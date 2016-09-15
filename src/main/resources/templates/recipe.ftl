<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/css/4-col-portfolio.css">
  
  <title>Management Recipe</title>
 
  <script src="${rc.getContextPath()}/webjars/jquery/3.1.0/jquery.min.js"></script>
  <script src="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
  
  <script>
    $( document ).ready( function() {
    	$('.btn_modifier').click(function() {
    		var recipeId = $(this).data('recipe_id');
    		$('#recipeForm' + recipeId).submit();
    	});
    	
    	$('.btn_remover').click(function() {
    		$(this).parent('form').submit();
    	});
    	
    	$('.btn_test').click(function() {
    		var recipeId = $(this).data('recipe_id'),
    		  formData = $('#recipeForm' + recipeId).serialize();
    		$.ajax({
    			'method' : 'get',
    			'data' : formData,
    			'url' : '${rc.getContextPath()}/preview/crawl',
    			'success' : function(response) {
    				console.log(response);
    			},
    			'error' : function() {
    				console.log('error');
    			}
    		});
    	});
    }) 
  </script>
</head>
<body>
  <#assign hasRecipe = recipes?size gt 0>
<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">News Crawler Management</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                      <a href="${rc.getContextPath()}/company/list">Company Management</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">News Recipe List
                <button id="newRecipe" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Recipe</button>
                </h1>
            </div>
        </div>
        
          <!-- Projects Row -->
      <#list recipes as recipe>
        <#if recipe?index % 4 == 0>
        <div class="row">
        </#if>
            <div class="col-md-3 portfolio-item">
              <div class="element-control-area">
                <a data-recipe_id="${recipe.recipeId}" class="btn_test" href="#">크롤테스트</a>
                <a data-recipe_id="${recipe.recipeId}" class="btn_modifier" href="#">수정</a>
                <form action="${rc.getContextPath()}/recipe/removal" method="post">
                  <a class="btn_remover" href="#">삭제</a>
                  <input type="hidden" value="${recipe.recipeId}" name="recipeId">
                  <input type="hidden" value="${companyId}" name="companyId">
                </form>
              </div>
              <div class="element-data-area">
                <form id="recipeForm${recipe.recipeId}" method="post" action="${rc.getContextPath()}/recipe/update">
                  <input type="hidden" value="${companyId}" name="companyId">
	                <input type="hidden" name="recipeId" value="${recipe.recipeId}">
	                <label for="seedUrl${recipe.recipeId}">Seed URL:</label>
	                <input type="url" class="form-control" id="seedUrl${recipe.recipeId}" name="seedUrl" value="${recipe.seedUrl}">
	                <label for="newsLinkSelector${recipe.recipeId}">NewsLink Selector:</label>
	                <input type="text" class="form-control" id="newsLinkSelector${recipe.recipeId}" name="newsLinkSelector" value="${recipe.newsLinkSelector}">
	                <label for="titleSelector${recipe.recipeId}">Title Selector:</label>
	                <input type="text" class="form-control" id="titleSelector${recipe.recipeId}" name="titleSelector" value="${recipe.titleSelector}">
	                <label for="contentSelector${recipe.recipeId}">Content Selector:</label>
	                <input type="text" class="form-control" id="contentSelector${recipe.recipeId}" name="contentSelector" value="${recipe.contentSelector}">
	                <label for="idSpot${recipe.recipeId}">Id Spot</label>
	                <select id="idSpot${recipe.recipeId}" name="idSpot">
	                  <option value="URL" <#if recipe.idSpot == "URL">selected="selected"</#if>>URL</option>
	                  <option value="DOCUMENT" <#if recipe.idSpot == 'DOCUMENT'>selected="selected"</#if>>DOCUMENT</option>
	                </select>
	                <label for="idExtractType${recipe.recipeId}">Id Extract Type</label>
	                <select id="idExtractType${recipe.recipeId}" name="idValueType">
	                  <option value="REGEX" <#if recipe.idValueType == 'REGEX'>selected="selected"</#if>>REGEX</option>
	                  <option value="VALUE" <#if recipe.idValueType == 'VALUE'>selected="selected"</#if>>VALUE</option>
	                  <option value="HTML" <#if recipe.idValueType == 'HTML'>selected="selected"</#if>>HTML</option>
	                  <option value="TEXT" <#if recipe.idValueType == 'TEXT'>selected="selected"</#if>>TEXT</option>
	                </select>
	                <label for="idSelector${recipe.recipeId}">Id Selector:</label>
	                <input type="text" class="form-control" id="idSelector${recipe.recipeId}" name="idSelector" value="${recipe.idSelector}">
                </form>
              </div>
            </div>
        <#if recipe?index % 4 == 3 || recipe?is_last>
        </div>
        </#if>
      </#list>
        <!-- /.row -->
        <hr>

			<div id="myModal" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title">레시피</h4>
			      </div>
			      <form id="companySubmit" method="post" action="${rc.getContextPath()}/recipe/new">
				      <div id="modal_body" class="modal-body">
				        <input type="hidden" name="companyId" value="${companyId}">
				        <label for="modal_recipe_seed_url">Seed URL:</label>
                <input type="url" class="form-control" id="modal_recipe_seed_url" name="seedUrl">
				        <label for="modal_recipe_news_link_selector">NewsLink Selector:</label>
	              <input type="text" class="form-control" id="modal_recipe_news_link_selector" name="newsLinkSelector">
	              <label for="modal_recipe_title_selector">Title Selector:</label>
	              <input type="text" class="form-control" id="modal_recipe_title_selector" name="titleSelector">
	              <label for="modal_recipe_content_selector">Content Selector:</label>
	              <input type="text" class="form-control" id="modal_recipe_content_selector" name="contentSelector">
	              <label for="modal_recipe_id_spot">Id Spot</label>
                <select id="modal_recipe_id_spot" name="idSpot">
                  <option value="URL" selected="selected">URL</option>
                  <option value="DOCUMENT">DOCUMENT</option>
                </select>
                <label for="modal_recipe_id_value_type">Id Extract Type</label>
                <select id="modal_recipe_id_value_type" name="idValueType">
                  <option value="REGEX" selected="selected">REGEX</option>
                  <option value="VALUE">VALUE</option>
                  <option value="HTML">HTML</option>
                  <option value="TEXT">TEXT</option>
                </select>
	              <label for="modal_recipe_id_selector">Id Selector:</label>
                <input type="text" class="form-control" id="modal_recipe_id_selector" name="idSelector">
				      </div>
				      <div class="modal-footer">
				        <input type="submit" class="btn btn-success" value="Submit">
				        <button type="button" class="btn btn-default modal_close" data-dismiss="modal">Close</button>
				      </div>
			      </form>
			    </div>
			
			  </div>
			</div>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->
</body>
</html>
