<ul>
   <g:each var="child" in="${ parent }">
      <li>
         <g:if test="${ child.key =~ /\.md/ }">
            <g:link action="view" params="[ parent: parent.key, file: child.key ]">${ child.key }</g:link>
         </g:if>
         <g:else>
            ${ child.key }
            <g:render template="step" model="[parent: child.value ]" /> 
         </g:else>
      </li>
   </g:each>
</ul>