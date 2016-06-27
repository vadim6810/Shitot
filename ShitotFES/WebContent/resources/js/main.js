$(document).ready(function() {
	var cal;
	var event_start = $('#event_start');
    var event_end = $('#event_end');
    var event_type = $('#event_type');  
    var calendar = $('#calendar');  
    var form = $('#dialog-form');
    var event_id = $('#event_id');
    var format = "MM/dd/yyyy HH:mm";
     
	$("#test").click(function() {
		// var cal = { "calendarId": 12, "calendarName" : "asfd" };
			console.log(calendar);
			$.ajax({
				method:"POST", 
				url : "editCalendar",
				dataType : "json",
				 contentType:"application/json",
				 data: JSON.stringify(calendar.val()),
					success : function(data) {
						 console.log(data);
					}           
			});
		});

	$('#calendar').fullCalendar({
		
		/*
		 * dayClick : function(){ alert('click'); }
		 */
		header : {
			left : 'prevYear, nextYear, prev, next, today',
			center : 'title',
			right : 'month, agendaWeek, agendaDay'
		},
		 buttonText: {
             prev: "קודם",
             next: "הבא",
             prevYear: "קודם שנה",
             nextYear: "הבא שנה",
             today: "היום",
             month: "חודש ",
             week: "שבוע",
             day: "יום"
         },
		selectable : true,
		selectHelper : true,
		
		select : function(start, end) {
			var title = 'Test';
			var eventData;
			if (title) {
				eventData = {
					title : title,
					start : start,
					end : end
				};
				
				$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
			}
			$('#calendar').fullCalendar('unselect');

			
		},
		
		events: {
              url: 'getCal',
              type : 'POST',
              error: function() {
                  alert('Ошибка соединения с источником данных!');
              }
          },
          
          dayClick: function(date, allDay, jsEvent, view) {
              var newDate = new Date();
              event_start.val(newDate);
              event_end.val(newDate);
              //formOpen('add');
          },
		 
		editable : true,
		eventLimit : true, // allow "more" link when too many events
		theme : true,
		lang : 'he',
		events : calendar.events

	});
	console.log(calendar);
});