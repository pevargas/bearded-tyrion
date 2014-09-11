<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Article</title>
	</head>
	<body>
		<g:each in="${articles}" var="title">
			<p><g:link action="view" params="[file: title]">${title}</g:link></p>
		</g:each>
	</body>
</html>