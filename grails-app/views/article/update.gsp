<!DOCTYPE html>
<html>
<head>
   <meta name="layout" content="miki"/>
   <title>${ title }</title>
</head>
<body>
   <g:form action="save">
      <g:textField name="file" value="${ title }" />
      <menu>
         <li><g:link action="view" params="[file:title]">Cancel</g:link></li>
         <li><g:submitButton name="save" value="Save" /></li>
      </menu>
      <p><b>Created</b> ${ created } | <b>Last Updated</b> ${ updated } | <b>Tags</b> <g:textField name="tags" value="${ tags.join(',') }" /></p>
      <g:textField name="old" value="${ title }" hidden="hidden"/>
      <g:textField name="created" value="${ created }" hidden="hidden"/>
      <g:textArea name="content" value="${ content }"/>
      </g:form>
</body>
</html>