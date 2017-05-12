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