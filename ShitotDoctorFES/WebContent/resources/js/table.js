		var table = document.getElementById("resultTable");
		var persons = JSON.parse('${patients}');
		for (var i = 0; i < persons.length; i++) {
			var row = table.insertRow(i+1);
			var date = new Date(persons[i].date);
			row.insertCell(0).innerHTML = persons[i].patientId;
			row.insertCell(1).innerHTML = date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear();
			row.insertCell(2).innerHTML = persons[i].name;
			row.insertCell(3).innerHTML = persons[i].telNumber;
			row.insertCell(4).innerHTML = persons[i].problem;
			row.insertCell(5).innerHTML = persons[i].age;
			row.insertCell(6).innerHTML = persons[i].payment;		
			row.insertCell(7).innerHTML = persons[i].whoSent;
			row.insertCell(8).innerHTML = persons[i].doctor.name;
			row.insertCell(9).innerHTML = persons[i].problems;		
		}
