
function getSelectCategory(){
	var checked_ids_category = $("#jstree_demo_category").jstree("get_checked",null,true);
    console.log(checked_ids_category);
    return checked_ids_category;
}


$('#jstree_demo_category').on('changed.jstree', function (e, data) {
    var path = data.instance.get_path(data.node,'/');
	
	currentPath = path;
	
	if(path){
		
		if(data.node.type === 'file'){

		}else{
							

		}
	}
	
})

function loadtree_category(){
	$('#jstree_demo_category').jstree({
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
	  "checkbox" : {
	    "keep_selected_style" : false
	  },
	  "plugins" : [
	    "contextmenu", "dnd", 
	    "state", "types", "wholerow", "checkbox"
	  ]
	});


}


