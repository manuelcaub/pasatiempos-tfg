function Table (headers, rows) {
	this.headers = headers;
	this.rows = rows;
}

Table.prototype.create = function () {
	var table = $('<table id="table" class="table table-bordered table-condensed"></table>');
	var trhead = $('<tr></tr>');
	var thead = $('<thead></thead>');
	var tbody = $('<tbody></tbody>');
	
	for(var i = 0; i < this.headers.length; i++) {
		trhead.append('<th>' + this.headers[i] + '</th>');
	}
	
	for(var i = 0; i < this.rows.length; i++) {
		var trbody = $('<tr></tr>');
		
		trbody.append('<td>' + this.rows[i].name + '</td>');			
		trbody.append('<td>' + this.rows[i].email + '</td>');
		trbody.append('<td>' + this.rows[i].roles + '</td>');
		
		tbody.append(trbody);
	}
	
	thead.append(trhead);
	table.append(thead);
	table.append(tbody);
	return table;
}