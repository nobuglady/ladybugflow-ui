
////////////////////////////////////////
// Initialize
////////////////////////////////////////
$(document).ready(function() {
	// register event
	$("#role-tab").on("click",loadRoleList());
	$("#user-tab").on("click",loadUserList());
	
	loadtree();
	loadtree_category();
	
	$('#jstree_demo').on('changed.jstree', function (e, data) {
	    var path = data.instance.get_path(data.node,'/');
		
		currentPath = path;
		
		if(path){
			
			if(data.node.type === 'file'){

			}else{
								

			}
		}
		
	})
	
});

////////////////////////////////////////
// JsTree
////////////////////////////////////////
function demo_create() {
	var ref = $('#jstree_demo').jstree(true),
		sel = ref.get_selected();
	if(!sel.length) { return false; }
	sel = sel[0];
	sel = ref.create_node(sel, {"type":"file"});
	if(sel) {
		ref.edit(sel);
	}
};
function demo_create_folder() {
	var ref = $('#jstree_demo').jstree(true),
		sel = ref.get_selected();
	if(!sel.length) { return false; }
	sel = sel[0];
	sel = ref.create_node(sel, {"type":"default"});
	if(sel) {
		ref.edit(sel);
	}
};
function demo_rename() {
	var ref = $('#jstree_demo').jstree(true),
		sel = ref.get_selected();
	if(!sel.length) { return false; }
	sel = sel[0];
	ref.edit(sel);
};
function demo_delete() {
	var ref = $('#jstree_demo').jstree(true),
		sel = ref.get_selected();
	if(!sel.length) { return false; }
	ref.delete_node(sel);
};

function loadtree(){
	$('#jstree_demo').jstree({
	  "core" : {
	    "animation" : 0,
	    "check_callback" : true,
	    "themes" : { "stripes" : true },
	    'data' : {
	      'url' : function (node) {
				if(node.id === '#'){
					return '/home/request_catagory_select';
				}else{
					return 'ajax_demo_children.json';
				}
	      },
	      'data' : function (node) {
	        return { 'id' : node.id };
	      }
	    }
	  },
	  "types" : {
	    "#" : {
	      "max_children" : 1,
	      "max_depth" : 4,
	      "valid_children" : ["root"]
	    },
	    "root" : {
	      "valid_children" : ["default"]
	    },
	    "default" : {
	      "icon" : "fa fa-sitemap",
	      "valid_children" : ["default","file"]
	    },
	    "file" : {
	      "icon" : "fa fa-user",
	      "valid_children" : []
	    },
	  },
	  "plugins" : [
	    "contextmenu", "dnd", "search",
	    "state", "types", "wholerow"
	  ]
	});
	  var to = false;
	  $('#plugins4_q').keyup(function () {
	    if(to) { clearTimeout(to); }
	    to = setTimeout(function () {
	      var v = $('#plugins4_q').val();
	      $('#jstree_demo').jstree(true).search(v,false,true);
	    }, 250);
	  });
	  $('#plugins4_q_1').keyup(function () {
	    if(to) { clearTimeout(to); }
	    to = setTimeout(function () {
	      var v = $('#plugins4_q_1').val();
	      $('#jstree_demo').jstree(true).search(v,false,true);
	    }, 250);
	  });


}


////////////////////////////////////////
// Send request
////////////////////////////////////////
/*
function request_catagory_update(catagoryList){
	
	postJson(
		'/home/request_catagory_update',
		{
			catagoryReqList:catagoryList
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			
		}
	);
	
}
*/
function request_catagory_delete(deleteIdList){
	
	console.log("delete:"+deleteIdList);
	
	postJson(
		'/home/request_catagory_delete',
		{
			deleteIdList:deleteIdList
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			
		}
	);
	
	
}
function request_catagory_rename(obj){
	
	console.log("rename:"+JSON.stringify(obj));
	
	postJson(
		'/home/request_catagory_rename',
		{
			id:obj.id,
			text:obj.text
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			
		}
	);
	
}

var tempId = null;
function request_catagory_create(obj){
	
	console.log("create:"+JSON.stringify(obj));
	
	//obj.id = getUniqueStr();
	
	postJson(
		'/home/request_catagory_create',
		{
			id:obj.id,
			text:obj.text,
			type:obj.type,
			parent:obj.parent
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			
		}
	);
	
	return obj;
}
function request_catagory_move(obj){
	
	console.log("move:"+JSON.stringify(obj));
	
	postJson(
		'/home/request_catagory_move',
		{
			id:obj.id,
			parent:obj.parent
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			
		}
	);
}
function request_catagory_copy(obj){
	
	console.log("copy:"+JSON.stringify(obj));

}


function getUniqueStr(myStrong){
 var strong = 1000;
 if (myStrong) strong = myStrong;
 return new Date().getTime().toString(16)  + Math.floor(strong*Math.random()).toString(16)
}
////////////////////////////////////////
// Click event
////////////////////////////////////////

/**
 * delete click
 */
function delete_show(type,name,id){
	$('#deleteModal').modal('show');
	$('#delete_type').val(type);
	$('#delete_name').text(name);
	$('#delete_id').val(id);
}

/**
 * delete click
 */
function delete_click(type,name,id){
	var type = $('#delete_type').val();
	var id = $('#delete_id').val();
	
	if("permission" == type){
		request_api_permission_delete(id);
	}else if("role" == type){
		request_api_role_delete(id);
	}else if("user" == type){
		request_api_user_delete(id);
	}
}

/**
 * click user create
 */
function user_create_click(){
	clearUserModel();
	$("#user_id").val("");
	$('#userCreateModal').modal('show');
	request_api_user_load_role();
}

/**
 * click role create
 */
function role_create_click(){
	$("#role_id").val("");
	$('#roleCreateModal').modal('show');
}


/////////////////////////////////
/**
 * role tab click
 */
function loadRoleList(){
	request_api_role_load_list();
}

/**
 * click role edit
 */
function role_edit_click(roleId){
	$('#roleCreateModal').modal('show');

	var roleJson = JSON.parse($("#role_row_"+roleId).val());
	
	$("#role_id").val(roleJson.roleId);
	$("#role_name").val(roleJson.roleName);
	$("#role_remarks").val(roleJson.remarks);
			
}


/**
 * click role save
 */
function save_role_click(){
	
	var validateResult = true;
	if($("#role_create_form")[0].checkValidity() == false){
		console.log("validate false.");
		validateResult = false;
	}
	
	$("#role_create_form")[0].classList.add('was-validated');
	
	if(validateResult){
		var roleId = $('#role_id').val();
		var roleName = $('#role_name').val();
		var roleRemarks = $('#role_remarks').val();
		
		request_role_save(roleId,roleName, roleRemarks);
	}
	
}

/////////////////////////////////
/**
 * user tab click
 */
function loadUserList(){
	request_api_user_load_list();
}

/**
 * click user edit
 */
function user_edit_click(userId){
	clearUserModel();
	$('#userCreateModal').modal('show');
	request_api_user_load_role(userId,
		
		function(userId){
			
			console.log("aaa");
			
			var userJson = JSON.parse($("#user_row_"+userId).val());
			
			var userDetailListJson = [];
			if($("#user_detail_row_"+userId).val() != ","){
				userDetailListJson = JSON.parse($("#user_detail_row_"+userId).val());
			}
			
			var userDetailCategoryListJson = [];
			if($("#user_detail_category_row_"+userId).val() != ","){
				userDetailCategoryListJson = JSON.parse($("#user_detail_category_row_"+userId).val());
			}
			
			
			$("#user_id").val(userJson.userId);
			$("#user_name").val(userJson.userName);
			$("#user_email").val(userJson.email);
			$("#user_password").val(userJson.passwd);
			$("#user_repassword").val(userJson.passwd);
			$("#user_remarks").val(userJson.remarks);
			
			for(detail of userDetailListJson){
				
				$("#user_role_check_"+detail.roleId).prop('checked', true);
				
			}
			
			
			for(detail of userDetailCategoryListJson){
				
				$("#jstree_demo_category").jstree("check_node",detail.categoryId);
				
			}
		}
	);
	

}

/**
 * click user save
 */
function save_user_click(){
	var validateResult = true;
	if($("#user_create_form")[0].checkValidity() == false){
		console.log("validate false.");
		validateResult = false;
	}
	
	var userPasswordChk = $('#user_password').val();
	var userRePasswordChk = $('#user_repassword').val();
	if(userPasswordChk != userRePasswordChk){
		validateResult = false;
	}
	
	$("#user_create_form")[0].classList.add('was-validated');
	
	if(validateResult){
		var userId = $('#user_id').val();
		var userName = $('#user_name').val();
		var userEmail = $('#user_email').val();
		var userPassword = $('#user_password').val();
		var userRemarks = $('#user_remarks').val();
		var userRoleList = [];
		$('input[name="user_role_check"]:checked').each(function(index, element){
			var roleId = $(element).val();
			console.log(role);
			
			userRoleList.push(
				{
					roleId:roleId
				}
			);
			
			
			
		});
		
		var checked_ids_category = getSelectCategory();
		
		request_user_save(userId, userName, userEmail, userPassword, userRemarks, userRoleList, checked_ids_category);
	}
	
}

////////////////////////////////////////
// Send request
////////////////////////////////////////

/////////////////////////////////

function request_api_role_delete(id){
	post(
		'/admin/request_api_role_delete',
		{
			'id':id
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			$('#deleteModal').modal('hide');
			request_api_role_load_list();
		}
	);
}

function request_api_role_load_list(){
	
	postJson(
		'/admin/request_role_load_list',
		{
			
		},
		function (data) {
	        var responseObj = data;
			$("#roleListBody").html("");
			for(role of responseObj.roleList){
				
				var newTrHtml = `
						    <tr>
						      <th scope="row">#id#</th>
						      <td>#name#</td>
						      <td>#remark#</td>
						      <td>
								<a href="javascript:role_edit_click(#id#)"><i class="fa fa-external-link fa-lg"></i></a> | 
								<a href="javascript:delete_show('role','#name#','#id#')"><i class="fa fa-trash-o fa-lg"></i></a>
								<input type="hidden" id="role_row_#id#" name="role_row_#id#" value='#role#'/>
								<input type="hidden" id="role_detail_row_#id#" name="role_detail_row_#id#" value='#detailList#'/>
						      </td>
						    </tr>
				`;
				
				newTrHtml = newTrHtml.split("#id#").join(role.roleId);
				newTrHtml = newTrHtml.split("#name#").join(role.roleName);
				newTrHtml = newTrHtml.split("#remark#").join(role.remarks);
				
				newTrHtml = newTrHtml.split("#role#").join(JSON.stringify(role));
				
				var existHtml = $("#roleListBody").html();
				
				$("#roleListBody").html(existHtml+newTrHtml);
			}
			
		}
	);
	
}

function request_role_save(roleId, roleName, roleRemarks){
	
	postJson(
		'/admin/request_role_save',
		{
			roleId:roleId,
			roleName:roleName,
			roleRemarks:roleRemarks
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			request_api_role_load_list();
			$('#roleCreateModal').modal('hide');
		}
	);
	
}

/////////////////////////////////

function request_api_user_delete(id){
	post(
		'/admin/request_api_user_delete',
		{
			'id':id
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			$('#deleteModal').modal('hide');
			request_api_user_load_list();
		}
	);
}

function request_api_user_load_list(){
	
	postJson(
		'/admin/request_user_load_list',
		{
			
		},
		function (data) {
	        var responseObj = data;
			$("#userListBody").html("");
			for(user of responseObj.userList){
				
				var newTrHtml = `
						    <tr>
						      <th scope="row">#id#</th>
						      <td>#email#</td>
						      <td>#name#</td>
						      <td>#detail#</td>
						      <td>#categoryDetail#</td>
						      <td>#remark#</td>
						      <td>
								<a href="javascript:user_edit_click(#id#)"><i class="fa fa-external-link fa-lg"></i></a> | 
								<a href="javascript:delete_show('user','#email#','#id#')"><i class="fa fa-trash-o fa-lg"></i></a>
								<input type="hidden" id="user_row_#id#" name="user_row_#id#" value='#user#'/>
								<input type="hidden" id="user_detail_row_#id#" name="user_detail_row_#id#" value='#detailList#'/>
								<input type="hidden" id="user_detail_category_row_#id#" name="user_detail_category_row_#id#" value='#detailCategoryList#'/>
						      </td>
						    </tr>
				`;
				
				var detail = responseObj.userDetailMap[user.userId];
				var categoryDetail = responseObj.userCategoryDetailMap[user.userId];
				if(!detail){
					detail = "";
				}
				if(!categoryDetail){
					categoryDetail = "";
				}
				var detailList = responseObj.userDetailListMap[user.userId];
				var detailCategoryList = responseObj.userCategoryDetailListMap[user.userId];
				
				newTrHtml = newTrHtml.split("#id#").join(user.userId);
				newTrHtml = newTrHtml.split("#name#").join(user.userName);
				newTrHtml = newTrHtml.split("#email#").join(user.email);
				newTrHtml = newTrHtml.split("#detail#").join(detail);
				newTrHtml = newTrHtml.split("#categoryDetail#").join(categoryDetail);
				newTrHtml = newTrHtml.split("#remark#").join(user.remarks);
				
				newTrHtml = newTrHtml.split("#user#").join(JSON.stringify(user));
				newTrHtml = newTrHtml.split("#detailList#").join(JSON.stringify(detailList));
				newTrHtml = newTrHtml.split("#detailCategoryList#").join(JSON.stringify(detailCategoryList));
				
				var existHtml = $("#userListBody").html();
				
				$("#userListBody").html(existHtml+newTrHtml);
			}
			
		}
	);
	
}

function request_api_user_load_role(userId, callback){
	
	postJson(
		'/admin/request_user_load_role',
		{
			
		},
		function (data) {
	        var responseObj = data;
			$("#user_create_list_id").html("");
			for(role of responseObj){
				
				var newTrHtml = `
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" value="#id#" name="user_role_check" id="user_role_check_#id#">
					  <label class="form-check-label" for="defaultCheck1">
					    #role_name#
					  </label>
					</div>
				`;
				
				newTrHtml = newTrHtml.split("#id#").join(role.roleId);
				newTrHtml = newTrHtml.split("#role_name#").join(role.roleName);
				
				var existHtml = $("#user_create_list_id").html();
				
				$("#user_create_list_id").html(existHtml+newTrHtml);
			}
			
			if(callback){
				callback(userId);
			}
			
		}
	);
	
}

function request_user_save(userId, userName, userEmail, userPassword, userRemarks, userRoleList, checked_ids_category){
	
	postJson(
		'/admin/request_user_save',
		{
			userId:userId,
			userName:userName,
			email:userEmail,
			passwd:userPassword,
			userRemarks:userRemarks,
			userRoleList:userRoleList,
			userCategoryList:checked_ids_category
		},
		function (data) {
	        var responseObj = data;
			console.log(responseObj);
			request_api_user_load_list();
			$('#userCreateModal').modal('hide');
		}
	);
	
}

function post(requestUrl, requestData, callBack){
    $.ajax({
        url:requestUrl,
        type:'POST',
        data:requestData
    }).done( (data, status, xhr) => {
        console.log(data);
		var ct = xhr.getResponseHeader("content-type") || "";
		if (ct.indexOf('html') > -1) {
		    window.location.replace("/login");
		}

        callBack(data);

    }).fail( (data) => {
        console.log(data);
    }).always( (data) => {

    });
}

function postJson(requestUrl, requestData, callBack){
    $.ajax({
        url:requestUrl,
        type:'POST',
		contentType: 'application/json',
		dataType: 'json',
        data:JSON.stringify(requestData)
    }).done( (data, status, xhr) => {
        console.log(data);
		var ct = xhr.getResponseHeader("content-type") || "";
		if (ct.indexOf('html') > -1) {
		    window.location.replace("/login");
		}

        callBack(data);
    }).fail( (data) => {
        console.log(data);
    }).always( (data) => {

    });
}

////////////////////////////////////////
// Helper function
////////////////////////////////////////

function clearUserModel(){
	$("#user_id").val("");
	$("#user_name").val("");
	$("#user_email").val("");
	$("#user_password").val("");
	$("#user_repassword").val("");
	$("#user_remarks").val("");
	
	$("input[name='user_role_check']:checkbox").prop('checked',false);
	$("#jstree_demo_category").jstree("uncheck_all");
	
}