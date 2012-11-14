<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>U-Key管理</title>
		<%@ include file="../include/meta.jsp"%>
		<script type="text/javascript">
	    Ext.require(['*']);
	
	    Ext.onReady(function() {
	
	        Ext.QuickTips.init();
	
	        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
	        
	        Ext.define('treeModel', {
			    extend: 'Ext.data.Model',
			    fields: [
			    	{name: 'id', type: 'int'},
			        {name: 'text', type: 'string'},
			        {name: 'url', type: 'string'},
			        {name: 'cls', type: 'string'},
			        {name: 'leaf', type: 'boolean'},
			        {name: 'expandable', type: 'boolean', convert: function(v, record){
			        	return false;
			        }},
			        {name: 'expanded', type: 'boolean', convert: function(v, record){
			        	return true;
			        }}
			    ]
			});
	        
			var treePanel = Ext.create('Ext.tree.Panel',{
            	id: 'menuTreePanel',
                region: 'center',
                title: '操作菜单',
                width: '70%',
                margins: '0 0 0 5',
                store: Ext.create('Ext.data.TreeStore', {
                	model: 'treeModel',
			        proxy: {
			            type: 'ajax',
			            url: '<%=request.getContextPath()%>/menu/tree.html'
			        },
			        root: {
			            text: '权限菜单',
			            id: '0',
			            expanded: true,
			            expandable: false
			        },
			        folderSort: true,
			        sorters: [{
			            property: 'text',
			            direction: 'ASC'
			        }]
			    }),
		        renderTo: Ext.getBody(),
		        rootVisible: true,
		        forceFit: true,
		        tools:[{
				    type:'refresh',
				    tooltip: '刷新',
				    handler: function(){
				    	Ext.getCmp('menuTreePanel').getStore().load();
				    }
				}],
				viewConfig: {
					plugins: {ptype: 'treeviewdragdrop'},
					listeners: {
			    		beforedrop: function(node, data, overModel, dropPosition, dropHandler, eOpts){
			    			if(overModel.data.leaf){
			    				Ext.Msg.alert('提醒','不能往叶子节点下面拖动！');
			    				return false;
			    			}
			    		},
			    		drop: function(node, data, overModel, dropPosition, eOpts){
			    			Ext.Ajax.request({
				   				url: '<%=request.getContextPath()%>/menu/drag.html',
				   				method: 'POST',
				   				params: {sourceId: data.records[0].data.id, targetId: overModel.data.id},
				   				success: function(response){
				   					var result = Ext.JSON.decode(response.responseText);
				   					console.dir(result);
				   					if(result.success){
				   						Ext.getCmp('menuTreePanel').getStore().load();
				   					}else{
				   						Ext.Msg.alert('提示','删除失败，请重试！');
				   					}
				   				},
				   				failure: function(response){
				   					Ext.Msg.alert('错误','删除失败，请重试！');
				   				}
				   			});
			    		}
			    	}
				},
		        listeners: {
		        	itemclick: function(v,record){
		        		view.down('propertygrid').setSource({
		        			'编号': record.data.id,
                            '名称': record.data.text,
                            'url': record.data.url,
                            'cls': record.data.cls,
                            '是否叶子': record.data.leaf
		        		});
		        	},
		        	itemcontextmenu: function(v, record, item, index, e, opts){
		        		e.stopEvent();
		        		var contextMenu = null;
		        		if(record.data.id==0){
		        			contextMenu = Ext.create('Ext.menu.Menu', {
							    floating: true,
							    renderTo: Ext.getBody(),
							    items: [
								    {
								    	xtype: 'menuitem',
								        text: '添加',
								        icon: '<%=request.getContextPath()%>/images/icons/fam/add.gif',
								        handler: function(item, e){
								        	if(record.data.leaf){
								        		Ext.Msg.alert('提醒','不能给叶子节点添加节点！');
								        		return false;
								        	}
								        	updateRecord('add', record.data.id);
								        }
								    }
							    ]
							});
		        		}else{
		        			contextMenu = Ext.create('Ext.menu.Menu', {
							    floating: true,
							    renderTo: Ext.getBody(),
							    items: [
								    {
								    	xtype: 'menuitem',
								        text: '添加',
								        icon: '<%=request.getContextPath()%>/images/icons/fam/add.gif',
								        handler: function(item, e){
								        	if(record.data.leaf){
								        		Ext.Msg.alert('提醒','不能给叶子节点添加节点！');
								        		return false;
								        	}
								        	updateRecord('add', record.data.id);
								        }
								    },{
								    	xtype: 'menuitem',
								        text: '修改',
								        icon: '<%=request.getContextPath()%>/images/icons/fam/cog_edit.png',
								        handler: function(item, e){
								        	updateRecord('update', record.data.id);
								        }
								    },{
								    	xtype: 'menuitem',
								        text: '删除',
								        icon: '<%=request.getContextPath()%>/images/icons/fam/delete.gif',
								        handler: function(item, e){
								        	Ext.Msg.confirm('提醒','确定要删除？',function(v){
								        		if(v=='yes'){
								        			deleteRecord(record);
								        		}
								        	});
								        }
								    }
							    ]
							});
		        		}
						contextMenu.showAt(e.getXY());
		        	},
		        	containercontextmenu: function(v, e, opts){
						e.stopEvent();
		        	}
		        }
            });
			
			var view = Ext.create('Ext.container.Viewport', {
		        renderTo: Ext.getBody(),
		        layout: 'border',
		        items: [
		        	treePanel,
		            {
		                xtype: 'propertygrid',
		                region: 'east',
		                title: '属性',
		                animCollapse: true,
		                collapsible: true,
		                width: '30%',
		                margins: '0 5 0 0',
		                renderTo: Ext.getBody(),
		                sortableColumns: false,
                        source: {
                            '编号': '',
                            '名称': '',
                            'url': '',
                            'cls': '',
                            '是否叶子': false
                        },
                        dockedItems: [{
		                    dock: 'top',
		                    xtype: 'toolbar',
		                    items: [ '->', {
								xtype: 'button',
								text: '刷新',
								handler: function(){
									var node = treePanel.getSelectionModel().getLastSelected();
									if(node!=null){
										this.up('viewport').down('propertygrid').setSource({
					        			'编号': node.data.id,
			                            '名称': node.data.text,
			                            'url': node.data.url,
			                            'cls': node.data.cls,
			                            '是否叶子': node.data.leaf
					        		});
									}
								}
		                    }]
		                }]
		            }
		        ]
		    });
			
			Ext.EventManager.onWindowResize(function(){
				view.doLayout();
			});
	    });
	    
	    function deleteRecord(record){
	    	Ext.Ajax.request({
   				url: '<%=request.getContextPath()%>/menu/delete.html',
   				method: 'POST',
   				params: {id: record.data.id},
   				success: function(response){
   					var result = Ext.JSON.decode(response.responseText);
   					console.dir(result);
   					if(result.success){
   						record.remove();
   					}else{
   						Ext.Msg.alert('提示','删除失败，请重试！');
   					}
   				},
   				failure: function(response){
   					Ext.Msg.alert('错误','删除失败，请重试！');
   				}
   			});
	    }
	    
	    function updateRecord(type, id){
	    
	    	var title = type=='add'?'增加':'修改';
	    	
	    	var formWin =Ext.create('Ext.Window', {
	  			id: 'updateWin',
		        title: title,
		        width: 500,
		        height: 250,
		        plain: true,
		        autoScroll: true,
		        buttonAlign: 'center',
		        modal: true,
		        resizable: false,
		        closable: false,
		        headerPosition: 'top',
		        layout: 'fit',
		        items: [
		            Ext.create('Ext.form.Panel', {
					    frame: true,
					    buttonAlign: 'center',
					    renderTo: Ext.getBody(),
					    layout: 'anchor',
					    bodyPadding: 10,
					    border: false,
					    defaults: {
					    	anchor: '100%'
					    },
					    fieldDefaults: {
				            msgTarget: 'side',
				            labelWidth: 75
				        },
				        defaultType: 'textfield',
					    items: [{
					    	xtype: 'fieldset',
					        title: '添加节点',
					        defaultType: 'textfield',
					        layout: 'anchor',
					        defaults: {
						    	anchor: '100%'
						    },
					        items: [{
						        name: 'id',
						        hidden: true
						    },{
						        name: 'parentId',
						        hidden: true
						    },{
						        fieldLabel: '节点名称',
						        name: 'text',
						        afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>',
						        allowBlank: false,
						        emptyText: '必填'
						    },{
						        fieldLabel: 'url',
						        name: 'url'
						    },{
						        fieldLabel: '样式类',
						        name: 'cls'
						    },{
						    	xtype: 'radiogroup',
						        fieldLabel: '是否叶子',
						        layout: 'hbox',
						        items: [
					                {boxLabel: '是', name: 'leaf', inputValue: 1},
					                {boxLabel: '否', name: 'leaf', inputValue: 0, checked: true}
					            ]
						    }]
				    	}],
					    buttons: [{
					        text: '提交',
					        handler: function() {
					            var form = this.up('form').getForm();
					            var win = this.up('form').up('window');
					            if (form.isValid()) {
					                form.submit({
					                	url: '<%=request.getContextPath()%>/menu/persist.html',
					                    success: function(form, action) {
					                    	if(action.result.success){
					                    		form.destroy();
					            				win.destroy();
					            				Ext.getCmp('menuTreePanel').getStore().load();
					                    	}else{
					                    		Ext.Msg.alert('成功', action.result.msg);
					                    	}
					                    },
					                    failure: function(form, action) {
					                        Ext.Msg.alert('失败', action.result.msg);
					                    }
					                });
					            }
					        }
					    }, {
					        text: '取消',
					        handler: function() {
					            this.up('form').getForm().destroy();
					            this.up('form').up('window').destroy();
					        }
					    }]
					})
		        ]
		    }).show();
		    
		    if(type!='add'){
			    formWin.down('form').getForm().load({
					url: '<%=request.getContextPath()%>/menu/load.html',
					method: 'POST',
					params: {id: id},
					failure: function(form, action) {
			        	Ext.Msg.alert('提示', '加载失败！');
				    }
				});
		    }else{
		    	formWin.down('form').getForm().setValues({parentId: id});
		    }
	    }
	    </script>
	</head>
	<body>
	</body>
</html>
