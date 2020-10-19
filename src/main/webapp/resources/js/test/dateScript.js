mobiscroll.settings = {
	    theme: 'ios',
	    themeVariant: 'light'
	};

	var now = new Date();

	mobiscroll.calendar('#demo-calendar-date-picker', {
	    display: 'inline',
	    onInit: function (event, inst) {
	        inst.setVal(now, true);
	    }
	});

	mobiscroll.calendar('#demo-calendar-header', {
	    display: 'bubble',
	    headerText: '{value}',
	    onInit: function (event, inst) {
	        inst.setVal(now, true);
	    }
	});

	mobiscroll.calendar('#demo-calendar-non-form', {
	    display: 'bubble',
	    onInit: function (event, inst) {
	        inst.setVal(now, true);
	    }
	});
	
	mobiscroll.calendar('#demo-calendar-non-form2', {
	    display: 'bubble',
	    onInit: function (event, inst) {
	        inst.setVal(now, true);
	    }
	});

	var instance = mobiscroll.calendar('#demo-calendar-date-external', {
	    display: 'bubble',
	    showOnTap: false,
	    showOnFocus: false,
	    onInit: function (event, inst) {
	        inst.setVal(new Date(), true);
	    }
	});

	document
	    .getElementById('show-demo-calendar-date-external')
	    .addEventListener('click', function () {
	        instance.show();
	    }, false);