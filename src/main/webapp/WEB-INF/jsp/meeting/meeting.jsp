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
	        
	        Extensible.calendar.data.CalendarMappings = {
			    CalendarId: {name:'CalendarId', mapping: 'id', type: 'int'},
			    Title: {name:'Title', mapping: 'title', type: 'string'},
			    Description: {name:'Description', mapping: 'description', type: 'string'},
			    ColorId: {name:'ColorId', mapping: 'colorId', type: 'int'},
			    IsHidden: {name:'IsHidden', mapping: 'isHidden', type: 'boolean'}
			};
			// Don't forget to reconfigure!
			Extensible.calendar.data.CalendarModel.reconfigure();
	        
	        var calendarStore = Ext.create('Extensible.calendar.data.MemoryCalendarStore', {
		        autoLoad: true,
		        proxy: {
		            type: 'ajax',
		            url: '<%=request.getContextPath()%>/meeting/color.html',
		            noCache: false,
		            reader: {
		                type: 'json',
		                root: 'calendars'
		            }
		        }
		    });
		    
		    Extensible.calendar.data.EventMappings = {
			    EventId: {name: 'EventId', mapping:'id', type:'int'},
			    CalendarId: {name: 'CalendarId', mapping: 'calendarId', type: 'int'},
			    Title: {name: 'Title', mapping: 'title'},
			    StartDate: {name: 'StartDate', mapping: 'startDate', type: 'date', dateFormat: 'Y-m-d H:i:s'},
			    EndDate: {name: 'EndDate', mapping: 'endDate', type: 'date', dateFormat: 'Y-m-d H:i:s'},
			    RRule: {name: 'RRule', mapping: 'recurRule'},
			    Location: {name: 'Location', mapping: 'location'},
			    Notes: {name: 'Notes', mapping: 'notes'},
			    Url: {name: 'Url', mapping: 'url'},
			    IsAllDay: {name: 'IsAllDay', mapping: 'isAllDay', type: 'boolean'},
			    Reminder: {name: 'Reminder', mapping: 'reminder'}
			};
			// Don't forget to reconfigure!
			Extensible.calendar.data.EventModel.reconfigure();
		    
		    var eventStore = Ext.create('Extensible.calendar.data.MemoryEventStore', {
		        autoLoad: true,
		        proxy: {
		            type: 'ajax',
		            url: '<%=request.getContextPath()%>/meeting/events.html',
		            noCache: false,
		            reader: {
						type: 'json',
            			root: 'evts'
		            },
		            writer: {
		                type: 'json',
		                nameProperty: 'mapping'
		            },
		            listeners: {
		                exception: function(proxy, response, operation, options){
		                    var msg = response.message ? response.message : Ext.decode(response.responseText).message;
		                    // ideally an app would provide a less intrusive message display
		                    Ext.Msg.alert('Server Error', msg);
		                }
		            }
		        },
		
		        // It's easy to provide generic CRUD messaging without having to handle events on every individual view.
		        // Note that while the store provides individual add, update and remove events, those fire BEFORE the
		        // remote transaction returns from the server -- they only signify that records were added to the store,
		        // NOT that your changes were actually persisted correctly in the back end. The 'write' event is the best
		        // option for generically messaging after CRUD persistence has succeeded.
		        listeners: {
		            'write': function(store, operation){
		                var title = Ext.valueFrom(operation.records[0].data[Extensible.calendar.data.EventMappings.Title.name], '');
		                switch(operation.action){
		                    case 'create': 
		                        alert(title);
		                        break;
		                    case 'update':
		                        alert(title);
		                        break;
		                    case 'destroy':
		                        alert(title);
		                        break;
		                }
		            }
		        }
		    });
		    
		    var cp = Ext.create('Extensible.calendar.CalendarPanel', {
		    	title: '会议预定',
		        width: '100%',
		        height: '100%',
		        renderTo: Ext.getBody(),
		        editModal: true,
		        showDayView: false,
		        showMonthView: false,
		        showMultiWeekView: false,
		        enableEditDetails: false,
		        todayText: '今天',
		        weekText: '周',
		        goText: '查询',
		        jumpToText: '选择日期',
		        eventStore: eventStore,
		        calendarStore: calendarStore,
		        weekViewCfg: {
		        	dayCount: 7,
		        	ddIncrement: 60,
		        	readOnly: false,
		        	scrollStartHour: 8,
		        	showHourSeparator: false,
		        	startDayIsStatic: true,
		        	viewStartHour: 8,
		        	viewEndHour: 17
		        },
		        listeners: {
		        	
		        }
		    });
		    
		    Ext.EventManager.onWindowResize(function(){
				cp.doLayout();
			});
	    });
	    </script>
	</head>
	<body>
	</body>
</html>
