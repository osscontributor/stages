<%@ page import="demo.TStage" %>



<div class="fieldcontain ${hasErrors(bean: TStageInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="TStage.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${TStageInstance?.name}"/>

</div>

