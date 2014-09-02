<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Articles</title>
</head>
<body>
	<g:each in="${articles}" var="art" status="i">
		<h3>${i + 1}. ${art.title}</h3>
		<p><b>Created:</b> ${art.created} <b>Updated:</b> ${art.updated}</p>
	</g:each>
</body>
</html>