<!DOCTYPE html>

<html>
<head>
<title>Add Student</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>
<body>

	<div id="wrapper">
		<div id="header">

			<h2>FooBar University</h2>

		</div>

	</div>

	<div id="container">
		<h3>Add Student</h3>

		<form action="StudentControllerServlet" method="GET">


			<!-- How to make different types of action in GET method -->
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="studentId" value="${STUDENT.id}" />

			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" 
						value = "${STUDENT.firstName}"/></td>
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" 
						value = "${STUDENT.lastName}" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" 
						value = "${STUDENT.email}"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Update" class="update" /></td>
					</tr>


				</tbody>




			</table>


		</form>
		<div style="clear: both;"></div>

		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>

</body>



</html>