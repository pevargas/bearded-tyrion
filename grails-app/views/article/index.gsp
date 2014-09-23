<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="miki"/>
    <title>Articles</title>
</head>
<body>
    <g:each var="parent" in="${ articles }">
      <g:render template="step" model="[parent: parent]" />
    </g:each>
</body>
</html>