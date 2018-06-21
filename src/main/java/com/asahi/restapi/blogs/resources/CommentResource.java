package com.asahi.restapi.blogs.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.asahi.restapi.blogs.entity.Comments;
import com.asahi.restapi.blogs.services.CommentService;
import com.asahi.restapi.blogs.services.CommentServiceImpl;

@Path("/Comments")

@Produces(value = { MediaType.APPLICATION_JSON})
public class CommentResource {

	CommentService commentService = new CommentServiceImpl();
	
	@GET
	public Response getComments(@PathParam("blogId") int blogId, @Context UriInfo uriInfo) throws URISyntaxException {

		String uriForself;

		List<Comments> allCommentsForSelectedBlog = commentService.getAllComments(blogId);

		String blogIdToString = Integer.toString(blogId);

		// System.out.println(uriInfo.getBaseUriBuilder().path(CommentResource.class).build().toString());

		for (Comments comments : allCommentsForSelectedBlog) {
			String id = Integer.toString(comments.getId());

			uriForself = uriInfo.getBaseUriBuilder().path("blogs").path(blogIdToString).path(CommentResource.class)
					.path(id).build().toString();
			comments.addLink(uriForself, "Self");
		}

		return Response.ok().entity(allCommentsForSelectedBlog.toArray(new Comments[allCommentsForSelectedBlog.size()]))
				.build();

	}

	@GET
	@Path("/{commentId}")
	public Response getSelectedComment(@PathParam("commentId") int commentId, @PathParam("blogId") int blogId,
			@Context UriInfo uriInfo) {
		String uri;
		Comments selectedComment = commentService.getSelectedComment(commentId);

		String neededId = Integer.toString(selectedComment.getId());

		String blogIdToString = Integer.toString(blogId);

		uri = uriInfo.getBaseUriBuilder().path("blogs").path(blogIdToString).path(CommentResource.class).path(neededId)
				.build().toString();

		selectedComment.addLink(uri, "Self");

		return Response.ok().entity(selectedComment).build();
	}

	@POST
	public Response addComments(Comments comments, @PathParam("blogId") int blogId,@Context UriInfo uriInfo) {
		Comments createdComment = commentService.addComment(comments, blogId);

		String createdId = Integer.toString(createdComment.getId());
		String blogIdToString = Integer.toString(blogId);
		
		String uri2 = uriInfo.getBaseUriBuilder()
				.path("/blogs")
				.path(blogIdToString)
				.path(CommentResource.class)
				.path(createdId)
				.build()
				.toString();
		
		createdComment.addLink(uri2, "Self");

		URI uri = uriInfo.getAbsolutePathBuilder().path(createdId).build();

		return Response.created(uri).entity(createdComment).build();
	}
	

	@DELETE
	@Path("/{commentId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteComment(@PathParam("commentId") int commentId) {
		String successMessage = commentService.deleteComment(commentId);

		return Response.accepted().entity(successMessage).build();
	}
}
