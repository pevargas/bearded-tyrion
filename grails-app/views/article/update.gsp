<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>Article</title>
    <style type="text/css">
      textarea {
        width: 100%;
        height: 30em;
        display: block;
        font-family: monospace;
      }
    </style>
  </head>
  <body>
    <header>
      <h1>${title}</h1>
    </header>
    <article>
      <g:form action="save">
        <g:textField name="file" value="${title}" hidden="hidden"/>
        <g:link action="index">Home</g:link>
        <g:link action="create">New</g:link>
        <g:link action="view" params="[file:title]">Cancel</g:link>
        <g:submitButton name="save" value="Save" />
        <g:textArea name="content" value="${ content }"/>
      </g:form>
    </article>

  </body>
</html>