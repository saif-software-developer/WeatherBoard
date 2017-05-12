var Wind= React.createClass({
	render: function() {
		var wind=this.props.wind;
		if(wind){
			return(<div>
				Wind Speed : {wind.speed}
				<br/>
				Wind deg   : {wind.deg}
				<br/>
				Wind gust  : {wind.gust}
				<br/>
			</div>);
		}else{
			return(<div>
			No data available 
		</div>);
		}
		
	}
});