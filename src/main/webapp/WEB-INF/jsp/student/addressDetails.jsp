<div id="addressInfo" style="display: none;">
    <spring-form:form method="POST" modelAttribute="address" class="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="inputLine1">Address line 1:</label>
            <div class="controls"><spring-form:input path="addressLine1" id="inputLine1" type="text" /></div>

            <label class="control-label" for="inputLine2">Address line 2:</label>
            <div class="controls"><spring-form:input path="addressLine2" id="inputLine2" type="text"/></div>

            <label class="control-label" for="inputSuburb">Suburb:</label>
            <div class="controls"><spring-form:input path="suburb" id="inputSuburb" type="text" /></div>

            <label class="control-label" for="inputPostcode">Postcode:</label>
            <div class="controls"><spring-form:input path="postcode" id="inputPostcode" type="text"/></div>

            <label class="control-label" for="inputState">State:</label>
            <div class="controls"><spring-form:input path="state" id="inputState" type="text"/></div>

            <label class="control-label" for="inputCountry">Country:</label>
            <div class="controls"><spring-form:input path="country" id="inputCountry" type="text"/>

            <button type="submit" class="btn btn-primary">Update info</button> </div>
        </div>
    </fieldset>
    </spring-form:form>
</div>