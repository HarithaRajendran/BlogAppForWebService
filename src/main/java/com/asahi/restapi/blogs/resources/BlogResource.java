package com.asahi.restapi.blogs.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.asahi.restapi.blogs.entity.Blogs;
import com.asahi.restapi.blogs.services.BlogService;
import com.asahi.restapi.blogs.services.BlogServiceImpl;

@Path("/blogs")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class BlogResource {

	BlogService blogService = new BlogServiceImpl();
	private @Context UriInfo uriInfo;

	@GET
	//@Produces(MediaType.APPLICATION_XML)
	public Response getAllBlogs() throws URISyntaxException {

		String uriForComment;
		String uriForself;

		List<Blogs> allBlogs = blogService.getAllBlogs();
		
		for(Blogs blogs:allBlogs) {
			String id = Integer.toString(blogs.getId());
			
			uriForComment = uriInfo.getBaseUriBuilder()
					.path(BlogResource.class)
					.path(id)
					.path("/Comments")
					.build()
					.toString();
			blogs.addLink(uriForComment, "Comments");
			
			uriForself =  uriInfo.getBaseUriBuilder()
					.path(BlogResource.class)
					.path(id)
					.build()
					.toString();
			blogs.addLink(uriForself, "Self");
		}
		

		return Response.ok().entity(allBlogs.toArray(new Blogs[allBlogs.size()])).build();
	}

	@GET
	@Path("/{blogId}")
	//@Produces(MediaType.APPLICATION_XML)
	public Response getSingleBlog(@PathParam("blogId") int id,@Context UriInfo uriInfo) {

		String uri;
		Blogs selectedBlog = blogService.getSingleBlog(id);
		
		String neededId = Integer.toString(selectedBlog.getId());
		
		uri = uriInfo.getBaseUriBuilder()
				.path(BlogResource.class)
				.path(neededId)
				.path("/Comments")
				.build()
				.toString();
		
		selectedBlog.addLink(uri, "Comments");

		return Response.ok().entity(selectedBlog).build();
	}

	@POST
	//@Produces(MediaType.APPLICATION_XML)
	//@Consumes(MediaType.APPLICATION_XML)
	public Response createBlog(Blogs blogs) {
		Blogs createdBlog = blogService.createBlog(blogs);

		String createdId = Integer.toString(createdBlog.getId());
		
		String uri2 = uriInfo.getBaseUriBuilder()
				.path(BlogResource.class)
				.path(createdId)
				.path("/Comments")
				.build()
				.toString();
		
		createdBlog.addLink(uri2, "Comments");

		URI uri = uriInfo.getAbsolutePathBuilder().path(createdId).build();

		return Response.created(uri).entity(createdBlog).build();
	}
	
	@DELETE
	@Path("/{blogId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteBlog(@PathParam("blogId") int id) {
		
		String successMessage = blogService.deleteBlog(id);
		
		return Response.accepted().entity(successMessage).build();
	}
	
	@PUT
	@Path("/{blogId}")
	//@Produces(MediaType.APPLICATION_XML)
	public Response updateBlog(@PathParam("blogId") int id,Blogs updatedBlogs) {

		Blogs updatedBlog = blogService.updateBlog(id,updatedBlogs);

		String UpdatedDataId = Integer.toString(updatedBlog.getId());
		
		String uri2 = uriInfo.getBaseUriBuilder()
				.path(BlogResource.class)
				.path(UpdatedDataId)
				.path("/Comments")
				.build()
				.toString();
		
		updatedBlog.addLink(uri2, "Comments");

		URI uri = uriInfo.getAbsolutePathBuilder().path(UpdatedDataId).build();

		return Response.created(uri).entity(updatedBlog).build();  
		
	}
	
	@GET
	@Path("/search")
	//@Produces(MediaType.APPLICATION_XML)
	public Response searchBlogByTitle(@QueryParam("title") String title) {
		
		String uri;

		List<Blogs> searchedBlogs = blogService.SearchBlogByTitle(title);
		
		for(Blogs blogs:searchedBlogs) {
			String id = Integer.toString(blogs.getId());
			
			uri = uriInfo.getBaseUriBuilder()
					.path(BlogResource.class)
					.path(id)
					.path("/Comments")
					.build()
					.toString();
			
			blogs.addLink(uri, "Comments");
		}
		
		uri = uriInfo.getAbsolutePath().toString();

		return Response.ok().entity(searchedBlogs.toArray(new Blogs[searchedBlogs.size()])).build();
		
	}
	
	@Path("/{blogId}/Comments")
	public CommentResource getCommentsResource() {
		
		return new CommentResource();
	}

}
