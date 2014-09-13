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
      <article>
         <aside>
            <g:link action="index">Home</g:link>
            <g:link action="create">New</g:link>
            <g:link action="update" params="[file:title]">Edit</g:link>
            <g:link action="delete" params="[file:title]">Remove</g:link>
            <p><b>Created</b> ${ created } | <b>Last Updated</b> ${ updated } | <b>Tags</b> ${ tags }</p>
         </aside>
         ${ raw( content ) }
      </article>

   </body>
</html>