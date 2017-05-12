var Main= React.createClass({
	render: function() {
		var main=this.props.main;
		if(main){
			return(<div>
			Temp : {main.temp}
			<br/>
			Pressure : {main.pressure}
			<br/>
			Humidity: {main.humidity}
			<br/>
			tempMain : {main.tempMin}
			<br/>
			tempMax : {main.tempMax}
			</div>);
		}else{
			return(<div>
			No data available 
		</div>);
		}
		
	}
});