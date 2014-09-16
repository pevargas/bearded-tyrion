<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="miki"/>
		<title>Article</title>
	</head>
	<body>
    <g:link action="create">New</g:link>
		<g:each in="${articles}" var="title">
			<p><g:link action="view" params="[file: title]">${title}</g:link></p>
		</g:each>
	</body>
</html>