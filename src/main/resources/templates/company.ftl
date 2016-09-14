<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/css/4-col-portfolio.css">
  
  <title>Management Company</title>
 
  <script src="${rc.getContextPath()}/webjars/jquery/3.1.0/jquery.min.js"></script>
  <script src="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
  
  <script>
    $( document ).ready( function() {
    	$('#newCompany').click(function() {
    		var $modalForm = $('#companySubmit');
    		$modalForm.attr('action', '${rc.getContextPath()}/company/new');
    	});
    	$('#newCompany').submit(function() {
    		var invalid = [];
    		$.each($(this).children('.modal-body').children('input'), function(idx, dom) {
    			var $dom = $(dom);
    			if (!$dom.val()) {
    			  invalid.push($dom.attr('name'));
    			}
    		});
    		if (invalid.length) {
    			alert(invalid.join(', ') + '입력해주세요!');
    			return false;
    		} else {
    			return true;
    		}
    	});
    	
    	$('.btn_modifier').click(function() {
    		var companyId = $(this).data('company_id');
    		var $valueElement = $('#element_' + companyId);
    		var img = $valueElement.children('img')[0];
    		var name = $valueElement.children('span#element_name_' + companyId)[0];
    		var description = $valueElement.children('span#element_description_' + companyId)[0];
    		var $hiddenId = $('#modal_company_id');
    		var $modalForm = $('#companySubmit');
    		if (!$hiddenId.length) {
    			$hiddenId = $('<input type="hidden" id="modal_company_id" name="id" val="' + companyId + '">');
    			$('#modal_body').append($hiddenId);
    		}
    		$hiddenId.val(companyId);
    		$('#modal_company_name').val(name.innerHTML);
    		$('#modal_company_image').val(img.alt !== 'no_image' ? img.src : null);
    		$('#modal_company_description').val(description.innerHTML);
    		
    		$modalForm.attr('action', '${rc.getContextPath()}/company/modification');
    		console.log($modalForm);
    		console.log($modalForm.attr('action'))
    	});
    	
    	$('.btn_remover').click(function() {
    		$(this).parent('form').submit();
    	});
    	
    	$('.modal_close').click(function() {
    		$('#modal_company_name').val('');
        $('#modal_company_image').val('');
        $('#modal_company_description').val('');
        $('#modal_company_id').remove();
    	});
    }) 
  </script>
</head>
<body>
  <#assign hasCompany = companies?size gt 0>
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
                    <li>Company</li>
                    <li>
                        <a href="${rc.getContextPath()}/recipe/all">Crawl Recipe</a>
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
                <h1 class="page-header">News Company List
                <button id="newCompany" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Company</button>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
      <#list companies as company>
        <#if company?index % 4 == 0>
        <div class="row">
        </#if>
            <div class="col-md-3 portfolio-item">
              <div class="element-control-area">
                <a data-company_id="${company.id}" class="btn_modifier" href="#" data-toggle="modal" data-target="#myModal">수정</a>
                <form action="${rc.getContextPath()}/company/removal" method="post">
                  <a class="btn_remover" href="#">삭제</a>
                  <input type="hidden" value="${company.id}" name="companyId">
                </form>
              </div>
              <div class="element-data-area">
                <#if company.image??>
                  <#assign img = company.image>
                  <#assign img_description = company.name>
                <#else>
                  <#assign img = rc.getContextPath() + "/image/default.jpg">
                  <#assign img_description = "no_image">
                </#if>
                <a id="element_${company.id}" href="${rc.getContextPath()}/recipe/${company.id}">
                    <img class="img-responsive" src="${img}" alt="${img_description}">
                    <span id="element_name_${company.id}">${company.name}</span>
                    <span id="element_description_${company.id}">${company.description}</span>
                </a>
              </div>
            </div>
        <#if company?index % 4 == 3 || company?is_last>
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
			        <h4 class="modal-title">뉴스제공사</h4>
			      </div>
			      <form id="companySubmit" method="post">
				      <div id="modal_body" class="modal-body">
				        
				          <label for="modal_company_name">Name:</label>
	                <input type="text" class="form-control" id="modal_company_name" name="name">
	                <label for="modal_company_description">Description:</label>
	                <input type="text" class="form-control" id="modal_company_description" name="description">
	                <label for="modal_company_image">Image URL:</label>
	                <input type="url" class="form-control" id="modal_company_image" name="image">
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
