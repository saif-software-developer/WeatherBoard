
//var Wind=require(['js/Wind']),
//Sys=require(['js/Sys']),
//Main=require(['js/Main']),
//Coord=require(['js/Coord']),
var WeatherManager= React.createClass({
		
	getInitialState: function() {
			
			var weather={
				dt:null
			};

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
			return(
			<div className="shape">
				<br/>
					<div id="place">{this.state.weather.name} </div>
					<Sys sys={this.state.weather.sys}/> 
					<span> Date : {new Date(this.state.weather.dt).toLocaleTimeString()}  </span> 
				<br/>
				<br/>
					<span> Cod  : {this.state.weather.cod}  </span>
				<br/>
					<span> Visibility : {this.state.weather.visibility}  </span> 
				<br/>
					<Coord coord={this.state.weather.coord}/>
				<br/>
					<Main main={this.state.weather.main}/>
				<br/>
					<Wind wind={this.state.weather.wind}/>
				<br/>
					<Weather weather={this.state.weather.weather}/>
			</div>
						
			);}
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

var Sys= React.createClass({
	render: function() {
		var sys=this.props.sys;
		if(sys){
			return(<div>
			<div id="place">{sys.country}</div>
			<br/>
			Sunrise : {new Date(sys.sunrise).toLocaleTimeString()}
			<br/>
			Sunset : {new Date(sys.sunset).toLocaleTimeString()}
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
			return(<div className="humidity-font">
			Temp : {main.temp}
			<br/>
			Pressure : {main.pressure}
			<br/>
			Humidity: {main.humidity}
			<br/>
			</div>);
		}else{
			return(<div>
			No data available 
		</div>);
		}
		
	}
});

var Wind= React.createClass({
	render: function() {
		var wind=this.props.wind;
		if(wind){
			return(<div>
				Wind Speed : {wind.speed}
				<br/>
				Wind deg   : {wind.deg}
				<br/>
			</div>);
		}else{
			return(<div>
			No data available 
		</div>);
		}
		
	}
});

var Weather= React.createClass({
	
	render: function() {
		var weather=this.props.weather;
		var imgSrc;
		if(weather){
			imgSrc='http://openweathermap.org/img/w/'+weather[0].icon+'.png';
		}
		if(weather){
		   return( <div>
				Main    : {weather[0].main}
				<br/>
				Description   : {weather[0].description}
				<br/>
				<img src={imgSrc} />
				
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
  document.getElementById('area')
);