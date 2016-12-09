$(document).ready(function(){
    expand = function(showi){
      if($(showi).hasClass("fa-chevron-down")){
        $(showi).removeClass("fa-chevron-down");
        $(showi).addClass("fa-chevron-up");
      }else if($(showi).hasClass("fa-chevron-up")){
        $(showi).removeClass("fa-chevron-up");
        $(showi).addClass("fa-chevron-down");
      }
    };
});

function saveSvg(svg) {
	  var svg_source = svg.outerHTML;
	  var svg_data_uri = 'data:image/svg+xml;base64,' + btoa(svg_source);
	  var link = document.createElement('a');
	  link.setAttribute('href', svg_data_uri);
	  link.setAttribute('download', 'svgFile.svg');
	  link.click();
}