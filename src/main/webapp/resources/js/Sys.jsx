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