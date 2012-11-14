<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="include/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>U-Key管理系统</title>
		<%@ include file="include/meta.jsp"%>
		<script type="text/javascript">
	    Ext.require(['*']);
	
	    Ext.onReady(function() {
	
	        Ext.QuickTips.init();
	
	        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
	        
	        var center = Ext.create('Ext.tab.Panel', {
            	id: 'center-tabpanel',
                region: 'center', // a center region is ALWAYS required for border layout
                deferredRender: false,
                renderTo: document.body,
                activeTab: 0,     // first tab initially active
                items: [{
                    title: '首页',
                    closable: true,
                    autoScroll: true,
                    html: '<iframe src="<%=request.getContextPath()%>/welcome.html" frameborder="0" width="100%" height="100%" frameborder="0" scrolling="auto" marginheight="0" marginwidth="0"></iframe>'
                }]
            });
            
            Ext.define('treeModel', {
			    extend: 'Ext.data.Model',
			    fields: [
			    	{name: 'id', type: 'string'},
			        {name: 'text', type: 'string'},
			        {name: 'url', type: 'string'},
			        {name: 'cls', type: 'string'},
			        {name: 'leaf', type: 'boolean'}
			    ]
			});
	
	        Ext.create('Ext.container.Viewport', {
	            layout: 'border',
	            items: [
	            {
	                region: 'north',
	                border: false,
	                bbar: [
	                	'->',
						{
							xtype: 'button',
							text: '个人信息',
							icon: '<%=request.getContextPath()%>/images/icons/fam/user.png',
							menu: {
								xtype: 'menu',
								items: [
									{
										xtype: 'menuitem',
										text: '修改信息',
										icon: '<%=request.getContextPath()%>/images/icons/fam/user_edit.png',
										handler: function(){
											alert('修改用户信息');
										}
									},
									{
										xtype: 'menuitem',
										text: '修改密码',
										icon: '<%=request.getContextPath()%>/images/icons/fam/folder_wrench.png',
										handler: function(){
											alert('修改密码');
										}
									},
									{
										xtype: 'menuitem',
										text: '忘记密码',
										icon: '<%=request.getContextPath()%>/images/icons/fam/information.png',
										handler: function(){
											alert('忘记密码');
										}
									}
								]
							}
						},'-',
						{
							xtype: 'button',
							text: '退出',
							handler: function(){
								alert('退出');
							}
						}
					]
	            }, {
	                // lazily created panel (xtype:'panel' is default)
	                region: 'south',
	                split: true,
	                collapsible: false,
	                collapsed: true,
	                hideCollapseTool: true
	            }, Ext.create('Ext.tree.Panel',{
	            	id: 'permissionTreePanel',
	                region: 'west',
	                title: '操作菜单',
	                split: true,
	                width: 200,
	                minWidth: 175,
	                maxWidth: 400,
	                collapsible: true,
	                animCollapse: true,
	                margins: '0 0 0 5',
	                store: Ext.create('Ext.data.TreeStore', {
	                	model: 'treeModel',
				        proxy: {
				            type: 'ajax',
				            url: '<%=request.getContextPath()%>/menu/tree.html'
				        },
				        root: {
				            text: '菜单',
				            id: 'src',
				            expanded: true
				        },
				        folderSort: true,
				        sorters: [{
				            property: 'text',
				            direction: 'ASC'
				        }]
				    }),
			        renderTo: Ext.getBody(),
			        useArrows: true,
			        rootVisible: false,
			        forceFit: true,
			        tools:[{
					    type:'refresh',
					    tooltip: '刷新',
					    handler: function(){
					    	Ext.getCmp('permissionTreePanel').getStore().load();
					    }
					},{
					    type:'expand',
					    tooltip: '全部展开',
					    handler: function(){
					    	Ext.getCmp('permissionTreePanel').expandAll();
					    }
					},{
					    type:'collapse',
					    tooltip: '全部收缩',
					    handler: function(){
					    	Ext.getCmp('permissionTreePanel').collapseAll();
					    }
					}],
			        listeners: {
			        	itemclick: function(view,record,node,index,event,opt){
			        		event.stopEvent();
			        		if(!record.data.leaf){
			        			if(record.isExpanded()){
			        				record.collapse();
			        				return;
			        			}else{
				        			record.expand();
				        			return;
			        			}
			        		}
			        		var tabpanel = Ext.getCmp('center-tabpanel');
			        		var tab = tabpanel.getComponent(record.data.id);
			        		if(tab){
			        			tab.show();
			        			return;
			        		}
			        		tabpanel.add({
			        			itemId: record.data.id,
			                    title: record.data.text,
			                    closable: true,
			                    autoScroll: true,
			                    html: '<iframe src="<%=request.getContextPath()%>'+record.data.url+'" frameborder="0" width="100%" height="100%" frameborder="0" scrolling="auto" marginheight="0" marginwidth="0"></iframe>'
			                }).show();
			        	},
			        	itemcontextmenu: function(v, record, item, index, e, opts){
			        		e.stopEvent();
			        	},
			        	containercontextmenu: function(v, e, opts){
							e.stopEvent();
			        	}
			        }
	            }),center]
	        });
	    });
	    </script>
	</head>
	<body>
	</body>
</html>
