<%@ page import="com.github.rjeschke.txtmark.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Article</title>
	</head>
	<body>
		<header>
			<h1>${title}</h1>
		</header>
		<article>${ raw( body ) }</article>

	</body>
</html>