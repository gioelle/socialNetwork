<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Social Network</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Social Network</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="glyphicon glyphicon-log-in" href="#"> Log out</a></li>
				<li><a class="glyphicon glyphicon-user" href="#"> Settings</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
	<div class="container home-container">

		<div class="container text-center">
			<div class="row">
				<div class="col-sm-3 well">
					<div class="well">
						<p>
							<a href="#"> <c:out value="${loggedInUser.firstName}"></c:out>'s
								Profile
							</a>
						</p>
						<img src="${loggedInUser.profilePic}" class="img-circle"
							height="65" width="65" alt="Avatar">
							<form method="POST" action="upload" enctype="multipart/form-data">
							<input type="file" name="file" />
							<br /><input type="submit" value="Submit"/>
							</form>

					</div>
					<div class="well">
						<p>
							<a href="#">Interests</a>
						</p>
						<p>
							<iframe
								src="https://www.facebook.com/plugins/like.php?href=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&width=450&layout=standard&action=like&size=large&show_faces=false&share=true&height=35&appId"
								width="450" height="35" style="border: none; overflow: hidden"
								scrolling="no" frameborder="0" allowTransparency="true"></iframe>

						</p>
					</div>
					<div class="alert alert-success fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
						<p>
							<strong>Ey!</strong>
						</p>
						People are looking at your profile. Find out who.
					</div>
					<p>
						<a href="https://www.linkedin.com/">LinkedIn</a>
					</p>
					<p>
						<a href="https://twitter.com/?lang=en">Twitter</a>
					</p>
					<p>
						<a href="https://mail.google.com">Gmail</a>
					</p>
				</div>
				<div class="col-sm-7">

					<div class="row">
						<div class="col-sm-12">
							<div class="panel panel-default text-left">
								<div class="panel-body">
									<form:form method="POST" modelAttribute="post" action="post">
										<form:input path="message" type="text" class="form-control"
											placeholder="Status: What's on your mind?"></form:input>
										<form:input path="email" type="hidden" class="form-control"
											value="${loggedInUser.email}"></form:input>
										<br>
										<button type="submit" class="btn btn-default btn-sm">
											<span class="glyphicon"></span> Post
										</button>
									</form:form>
								</div>
							</div>
						</div>
					</div>



	<c:forEach var="post" items="${posts}">
	<!--  this is just one post message from here....  -->
					<div class="row">
						<div class="col-sm-3">
							<div class="well">
								<p> <!-- because we joined the post and person tables  -->
									<c:out value="${post.person.firstName}"/>
								</p>
								<img src="${loggedInUser.profilePic}" class="img-circle" height="55"
									width="55" alt="Avatar">
							</div>
						</div>
						<div class="col-sm-9">
							<div class="well">
								<p>
									<c:out value="${post.message}"/>
								</p>
							</div>
							<button type="button" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-thumbs-up"></span> Like
							</button>
						</div>
					</div>
	<!--  to here -->
	</c:forEach>

				</div>
				<div class="col-sm-2 well">
					<div class="thumbnail">
						<p>Upcoming Events:</p>
						<img src="" alt="Claim" width="400" height="300">
						<p>
							<strong>Demo Day</strong>
						</p>
						<p>Thu.24 August 2017</p>
						<button class="btn btn-primary">Info</button>
					</div>
					<div class="well">
						<p>ADS</p>
					</div>
					<div class="well">
						<p>ADS</p>
					</div>
				</div>
			</div>
		</div>



	</div>
	<!-- /.container -->

</body>
</html>