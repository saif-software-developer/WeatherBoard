



var WeatherManager= React.createClass({
		
	getInitialState: function() {
			
			var weather={};

			return {
				weather
			};
			
		},
		componentDidMount() {
			   fetch('http://localhost:8080/demo/async/weatherInCity') 
		           .then(result=>result.json())
		           .then(weather=>this.setState({weather}));
		},
		render: function() {
			return(<div id="section">
				<span> City : {this.state.weather.name} </span> 
				<br/>
				<span> Date :  {this.state.weather.dt}  </span> 
				<br/>
				<span> Cod  : {this.state.weather.cod}  </span>
				<br/>
				<span> Visibility : {this.state.weather.visibility}  </span> 
				<br/>
				<span> Base : {this.state.weather.base}  </span> 
				<br/>
				<span> Id : {this.state.weather.id}   </span> 
				<br/>
				<span> Wind : </span> 
				<Wind wind={this.state.weather.wind}/>
				<br/>
				<span> Sys : </span> 
					<Sys sys={this.state.weather.sys}/>
				<br/>
		
				<span> Coord : </span> 
					<Coord coord={this.state.weather.coord}/>
				<br/>
					<span> Main : </span> 
					<Main main={this.state.weather.main}/>
				<br/>
				
			</div>);
		}
});


var Coord= React.createClass({
	render: function() {
		var coord=this.props.coord;
		if(coord){
			return(<div>
			Coord Lon: {coord.lon}
			<br/>
			Coord Lat:  {coord.lat}
			<br/>
			</div>);
		}else{
			return(<div>
			No data available 
		</div>);
		}
		
	}
}); 

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

var Sys= React.createClass({
	render: function() {
		var sys=this.props.sys;
		if(sys){
			return(<div>
			type : {sys.type}
			<br/>
			id : {sys.id}
			<br/>
			message: {sys.message}
			<br/>
			country : {sys.country}
			<br/>
			sunrise : {sys.sunrise}
			<br/>
			sunset : {sys.sunset}
			</div>);
		}else{
			return(<div>
			No data available 
		</div>);
		}
		
	}
}); 




// Render 
ReactDOM.render( <WeatherManager /> ,
  document.getElementById('main')
);