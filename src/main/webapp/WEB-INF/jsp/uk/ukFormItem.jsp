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
	        
	        Ext.define('UkFormItem', {
			    extend: 'Ext.data.Model',
			    fields: [
			       {name: 'id', type:'int'},
			       {name: 'appFormId', type: 'int'},
			       {name: 'applicant', type: 'string'},
			       {name: 'isUk', type: 'boolean'},
			       {name: 'adName', type: 'string'},
			       {name: 'isTms', type: 'boolean'},
			       {name: 'tmsAccount', type: 'string'},
			       {name: 'isNew', type: 'boolean'},
			       {name: 'isSpecial', type: 'boolean'},
			       {name: 'threeLevelDept', type: 'string'},
			       {name: 'fourLevelDept', type: 'string'},
			       {name: 'postalAddress', type: 'string'},
			       {name: 'postcode', type: 'string'},
			       {name: 'position', type: 'string'},
			       {name: 'identityCardNo', type: 'string'},
			       {name: 'phone', type: 'string'},
			       {name: 'loginMode', type: 'int'},
			       {name: 'remark', type: 'string'}
			    ],
			    idProperty: 'id'
			});
	        
			var store = Ext.create('Ext.data.Store', {
			    remoteSort: true,
			    model: 'UkFormItem',
			    pageSize: 20, // items per page
			    proxy: {
			        type: 'ajax',
			        url: '<%=request.getContextPath()%>/ukFormItem/list.html',
			        reader: {
			            type: 'json',
			            root: 'list',
			            totalProperty: 'total'
			        }
			    },
			    listeners: {
			    	refresh: function(s){
			    		if(s.getCount()==0){
			    			Ext.getCmp('pagingtoolbar').movePrevious();
			    		}
			    	}
			    }
			});
			
			store.loadPage(1);
			
			var grid = Ext.create('Ext.grid.Panel', {
			    title: 'U-Key申请人列表',
			    region: 'north',
			    width: '100%',
			    height: '70%',
			    //forceFit: true,
			    columnLines: true,
			    rowLines: true,
			    split: true,
			    renderTo: Ext.getBody(),
			    store: store,
			    selModel: Ext.create('Ext.selection.CheckboxModel',{
			    	ignoreRightMouseSelection: true
			    }),
			    columns: [
			    	{xtype: 'rownumberer', text: '编号', align: 'center', hideable: true, width: 40},
			        {xtype: 'gridcolumn', text: '主键',  dataIndex: 'id', align: 'center', width: '10%', hidden: true},
			        {xtype: 'gridcolumn', text: '申请表', dataIndex: 'appFormId', align: 'center', width: '10%', hidden: true},
			        {xtype: 'gridcolumn', text: '申请人', dataIndex: 'applicant', align: 'center', width: '10%'},
			        {
			        	xtype: 'gridcolumn',
			        	text: '是否UK',
			        	dataIndex: 'isUk',
			        	align: 'center',
			        	width: '10%',
			        	renderer: function(value){
			        		if(value){
			        			return '是';
			        		}else{
			        			return '否';
			        		}
			        	}
			        },
			        {xtype: 'gridcolumn', text: '活动目录', dataIndex: 'adName', align: 'center', width: '10%'},
			        {
			        	xtype: 'gridcolumn',
			        	text: '是否TMS支付',
			        	dataIndex: 'isTms',
			        	align: 'center',
			        	width: '10%',
			        	renderer: function(value){
			        		if(value){
			        			return '是';
			        		}else{
			        			return '否';
			        		}
			        	}
			        },
			        {xtype: 'gridcolumn', text: 'TMS账号', dataIndex: 'tmsAccount', align: 'center', width: '10%'},
			        {
			        	xtype: 'gridcolumn',
			        	text: '是否新增',
			        	dataIndex: 'isNew',
			        	align: 'center',
			        	width: '10%',
			        	renderer: function(value){
			        		if(value){
			        			return '是';
			        		}else{
			        			return '否';
			        		}
			        	}
			        },
			        {
			        	xtype: 'gridcolumn',
			        	text: '是否专机',
			        	dataIndex: 'isSpecial',
			        	align: 'center',
			        	width: '10%',
			        	renderer: function(value){
			        		if(value){
			        			return '是';
			        		}else{
			        			return '否';
			        		}
			        	}
			        },
			        {xtype: 'gridcolumn', text: '三级部门', dataIndex: 'threeLevelDept', align: 'center', width: '10%'},
			        {xtype: 'gridcolumn', text: '四级部门', dataIndex: 'fourLevelDept', align: 'center', width: '10%'},
			        {xtype: 'gridcolumn', text: '通讯地址', dataIndex: 'postalAddress', align: 'center', width: '10%'},
			        {xtype: 'gridcolumn', text: '邮编', dataIndex: 'postcode', align: 'center', width: '10%'},
			        {xtype: 'gridcolumn', text: '职务', dataIndex: 'position', align: 'center', width: '10%'},
			        {xtype: 'gridcolumn', text: '身份证号', dataIndex: 'identityCardNo', align: 'center', width: '10%'},
			        {xtype: 'gridcolumn', text: '电话', dataIndex: 'phone', align: 'center', width: '10%'},
			        {
			        	xtype: 'gridcolumn',
			        	text: '登录方式',
			        	dataIndex: 'loginMode',
			        	align: 'center',
			        	width: '10%',
			        	renderer: function(value){
			        		if(value==1){
			        			return '专用';
			        		}else{
			        			return '公网';
			        		}
			        	}
			        },
			        {xtype: 'gridcolumn', text: '备注', dataIndex: 'remark', align: 'center', width: '10%'},
			        {
			        	xtype: 'actioncolumn',
			        	text: '操作',
			        	menuDisabled: true,
			        	align: 'center',
			        	width: '10%',
			        	items: [
				        	{
			                    icon: '<%=request.getContextPath()%>/images/icons/fam/cog_edit.png',
			                    tooltip: '修改',
			                    handler: function(grid, rowIndex, colIndex) {
			                        updateRecord('update', grid.getStore().getAt(rowIndex).get('id'));
			                    }
			                }, {
			                    icon: '<%=request.getContextPath()%>/images/icons/fam/delete.gif',
			                    tooltip: '删除',
			                    handler: function(grid, rowIndex, colIndex) {
			                    	Ext.Msg.confirm('提醒','确定删除？',function(v){
			                    		if(v=='yes'){
			                    			deleteRecord(grid.getStore().getAt(rowIndex).get('id'));
			                    		}
			                    	});
			                    }
			                }
		                ]
			        }
			    ],
			    tbar: [
			    	{
			    		xtype: 'button',
			    		text: '增加',
			    		icon: '<%=request.getContextPath()%>/images/icons/fam/add.png',
			    		handler: function(){
			    			 updateRecord('add',null);
			    		}
			    	},'-',{
			    		xtype: 'button',
			    		text: '修改',
			    		icon: '<%=request.getContextPath()%>/images/icons/fam/cog_edit.png',
			    		handler: function(){
			    			var record = grid.getSelectionModel().getLastSelected();
			    			if(record!=null){
			    				updateRecord('update', record.get('id'));
			    			}else{
			    				Ext.Msg.alert('提示','请选择要修改的记录！');
			    			}
			    		}
			    	},'-',{
			    		xtype: 'button',
			    		text: '删除',
			    		icon: '<%=request.getContextPath()%>/images/icons/fam/delete.gif',
			    		handler: function(){
			    			var selections = grid.getSelectionModel().getSelection();
			    			if(selections.length>0){
			    				var ids = new Array();
			    				Ext.each(selections,function(s){
			    					ids.push(s.get('id'));
			    				});
			    				Ext.Msg.confirm('提醒','确定删除？',function(v){
		                    		if(v=='yes'){
		                    			deleteRecord(ids);
		                    		}
		                    	});
			    			}else{
			    				Ext.Msg.alert('提示','请选择要删除的记录！');
			    			}
			    		}
			    	}
			    ],
			    dockedItems: [{
			    	id: 'pagingtoolbar',
			        xtype: 'pagingtoolbar',
			        store: store,
			        dock: 'bottom',
			        displayInfo: true
			    }],
			    viewConfig: {
		            stripeRows: true,
		            enableTextSelection: false
		        },
		        listeners: {
		        	itemdblclick: function(view, record){
		        		updateRecord('update', record.get('id'));
		        	}
		        }
			});
			
			var view = Ext.create('Ext.container.Viewport', {
		        renderTo: Ext.getBody(),
		        layout: 'border',
		        items: [
		        	grid,
		            Ext.create('Ext.form.Panel', {
					    title: '查询',
					    frame: true,
					    region: 'center',
					    autoScroll: true,
					    border: false,
					    buttonAlign: 'center',
					    renderTo: Ext.getBody(),
					    layout: 'anchor',
					    bodyPadding: 10,
					    fieldDefaults: {
				            labelWidth: 75
				        },
					    defaultType: 'textfield',
					    items: [{
					    	xtype: 'fieldset',
					        title: '查询条件',
					        defaultType: 'radio',
					        layout: 'anchor',
					        items: [{
					    		xtype: 'textfield',
						        fieldLabel: '申请人',
						        name: 'applicant',
						        width: 300
						    }]
				    	}],
					    buttons: [{
					        text: '查询',
					        handler: function() {
					            var form = this.up('form').getForm();
					            store.on('beforeload',function(){
					            	Ext.apply(store.proxy.extraParams, {applicant: form.getValues()['applicant']});
					            });
					            Ext.getCmp('pagingtoolbar').moveFirst();
					        }
					    }, {
					        text: '重置',
					        handler: function() {
					            this.up('form').getForm().reset();
					        }
					    }]
					})
		        ]
		    });
			
			Ext.EventManager.onWindowResize(function(){
				view.doLayout();
			});
	    });
	    
	    function deleteRecord(ids){
	    	Ext.Ajax.request({
   				url: '<%=request.getContextPath()%>/ukFormItem/delete.html',
   				method: 'POST',
   				params: {id: ids},
   				success: function(response){
   					var result = Ext.JSON.decode(response.responseText);
   					console.dir(result);
   					if(result.success){
   						Ext.getCmp('pagingtoolbar').doRefresh();
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
	    
	    	Ext.regModel('UkAppForm', {
			    fields: [
			        {type: 'int', name: 'id'},
			        {type: 'string', name: 'name'}
			    ]
			});
	    
	    	var ukAppFormStore = Ext.create('Ext.data.JsonStore',{
	    		autoLoad: true,
	    		remoteSort: true,
			    model: 'UkAppForm',
			    pageSize: 10,
			    proxy: {
			        type: 'ajax',
			        url: '<%=request.getContextPath()%>/ukAppForm/list.html',
			        reader: {
			            type: 'json',
			            root: 'list',
			            totalProperty: 'total'
			        }
			    },
			    listeners: {
			    	load: function(store, records){
			    		if(ukAppFormCombo.getValue() != null){
				    		Ext.Ajax.request({
				   				url: '<%=request.getContextPath()%>/ukAppForm/load.html',
				   				method: 'POST',
				   				params: {id: ukAppFormCombo.getValue()},
				   				success: function(response){
				   					var result = Ext.JSON.decode(response.responseText);
				   					console.dir(result);
				   					if(result.success){
				   						store.add(result.data);
				   						ukAppFormCombo.setValue(result.data.id);
				   					}else{
				   						Ext.Msg.alert('提示','加载失败，请重试！');
				   					}
				   				},
				   				failure: function(response){
				   					Ext.Msg.alert('错误','加载失败，请重试！');
				   				}
				   			});
			    		}
			    	}
			    }
	    	});
	    	
	    	var ukAppFormCombo = Ext.create('Ext.form.field.ComboBox', {
			    fieldLabel: '申请表',
			    name: 'appFormId',
			    afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>',
			    emptyText: '必选',
			    store: ukAppFormStore,
			    queryMode: 'remote',
			    pageSize: 10,
			    typeAhead: true,
			    triggerAction: 'all',
			    editable: false,
			    allowBlank: false,
			    forceSelection: true,
			    displayField: 'name',
			    valueField: 'id'
			});
	    
	    	var title = type=='add'?'增加':'修改';
	    	
	    	var formWin =Ext.create('Ext.Window', {
	  			id: 'updateWin',
		        title: title,
		        width: 600,
		        height: 500,
		        plain: true,
		        autoScroll: true,
		        buttonAlign: 'center',
		        modal: true,
		        resizable: true,
		        closable: false,
		        headerPosition: 'top',
		        layout: 'fit',
		        items: [
		            Ext.create('Ext.form.Panel', {
					    frame: true,
					    buttonAlign: 'center',
					    renderTo: Ext.getBody(),
					    layout: 'anchor',
					    autoScroll: true,
					    bodyPadding: 10,
					    border: false,
					    defaults: {
					    	anchor: '100%'
					    },
					    fieldDefaults: {
				            msgTarget: 'side',
				            labelWidth: 100
				        },
				        defaultType: 'textfield',
					    items: [{
					    	xtype: 'fieldset',
					        title: 'U-Key申请人信息',
					        defaultType: 'textfield',
					        layout: 'anchor',
					        defaults: {
						    	anchor: '100%'
						    },
					        items: [{
						        name: 'id',
						        hidden: true
						    },ukAppFormCombo,{
						        fieldLabel: '申请人',
						        name: 'applicant',
						        afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>',
						        allowBlank: false,
						        emptyText: '必填'
						    },{
						    	xtype: 'radiogroup',
						        fieldLabel: '是否申请UK',
						        layout: 'hbox',
						        items: [
					                {boxLabel: '是', name: 'isUk', inputValue: 1, checked: true},
					                {boxLabel: '否', name: 'isUk', inputValue: 0}
					            ]
						    },{
						        fieldLabel: '活动目录',
						        name: 'adName',
						        afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>',
						        allowBlank: false,
						        emptyText: '必填'
						    },{
						    	xtype: 'radiogroup',
						        fieldLabel: '是否登录TMS支付',
						        layout: 'hbox',
						        items: [
					                {boxLabel: '是', name: 'isTms', inputValue: 1, checked: true},
					                {boxLabel: '否', name: 'isTms', inputValue: 0}
					            ]
						    },{
						        fieldLabel: 'TMS账号',
						        name: 'tmsAccount'
						    },{
						    	xtype: 'radiogroup',
						        fieldLabel: '是否新增用户',
						        layout: 'hbox',
						        items: [
					                {boxLabel: '是', name: 'isNew', inputValue: 1, checked: true},
					                {boxLabel: '否', name: 'isNew', inputValue: 0}
					            ]
						    },{
						    	xtype: 'radiogroup',
						        fieldLabel: '是否需要专机',
						        layout: 'hbox',
						        items: [
					                {boxLabel: '是', name: 'isSpecial', inputValue: 1, checked: true},
					                {boxLabel: '否', name: 'isSpecial', inputValue: 0}
					            ]
						    },{
						        fieldLabel: '三级单位',
						        name: 'threeLevelDept'
						    },{
						        fieldLabel: '四级单位',
						        name: 'fourLevelDept'
						    },{
						        fieldLabel: '通讯地址',
						        name: 'postalAddress'
						    },{
						        fieldLabel: '邮编',
						        name: 'postcode'
						    },{
						        fieldLabel: '职务',
						        name: 'position'
						    },{
						        fieldLabel: '身份证号',
						        name: 'identityCardNo',
						        afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>',
						        allowBlank: false,
						        emptyText: '必填',
						        validator: function(value){
						        	if(value.length==15 || value.length==18){
						        		return true;
						        	}else{
						        		return '身份证号为15或18位';
						        	}
						        }
						    },{
						        fieldLabel: '电话',
						        name: 'phone',
						        afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>',
						        allowBlank: false,
						        emptyText: '必填'
						    },{
						    	xtype: 'radiogroup',
						        fieldLabel: '登录方式',
						        layout: 'hbox',
						        items: [
					                {boxLabel: '专用网', name: 'loginMode', inputValue: 1, checked: true},
					                {boxLabel: '公网', name: 'loginMode', inputValue: 2}
					            ]
						    },{
						    	xtype: 'htmleditor',
						        fieldLabel: '备注',
						        name: 'remark',
						        height: 150
						    }]
				    	}],
					    buttons: [{
					        text: '提交',
					        handler: function() {
					            var form = this.up('form').getForm();
					            var win = this.up('form').up('window');
					            if (form.isValid()) {
					                form.submit({
					                	url: '<%=request.getContextPath()%>/ukFormItem/persist.html',
					                    success: function(form, action) {
					                    	if(action.result.success){
					                    		form.destroy();
					            				win.destroy();
					                    		Ext.getCmp('pagingtoolbar').doRefresh();
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
					url: '<%=request.getContextPath()%>/ukFormItem/load.html',
					method: 'POST',
					params: {id: id},
					failure: function(form, action) {
			        	Ext.Msg.alert('提示', '加载失败！');
				    }
				});
		    }
	    }
	    </script>
	</head>
	<body>
	</body>
</html>
