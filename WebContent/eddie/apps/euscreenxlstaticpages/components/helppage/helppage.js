var Helppage = function () {
    Component.apply(this, arguments);
    
//  when you click on the anchor tag we remove the "showtext" class
    $(".triggers").click(function() {
	  $(".play").each(function() {
		  $(".play").removeClass("showtext");
	  });
	  // we add the "showtext" class only for the clicked element
	  $(this).find('.play').addClass("showtext");
	});
    
};

Helppage.prototype = Object.create(Component.prototype);
