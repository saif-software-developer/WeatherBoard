var WeatherManager= React.createClass(
	{
		getInitialState: function() {
			
			var defaultComp=[];

			return {
				defaultComp
			};
			
		},
		render: function() {
		
		var defaultComp=this.props.fields;
		var defaultUI=[];
		for(var compIndex=0;compIndex<defaultComp.length;compIndex++){
			//Push label first 
			defaultUI.push(
				<label>
					{defaultComp[compIndex]['label']}
				</label>
				);

			if(defaultComp[compIndex]['type']==='input'){
				defaultUI.push(
					<input name={defaultComp[compIndex]['path']} value={defaultComp[compIndex]['value']}/>
				);
			}else{
				var optionsArr=defaultComp[compIndex]['value'];
				var optionsUI=[];
				for(var optionIndex=0;optionIndex<optionsArr.length;optionIndex++){
					optionsUI.push(
							 <option value={optionsArr[optionIndex]}>{optionsArr[optionIndex]}</option>
						);
				}
				defaultUI.push(
					<select name={defaultComp[compIndex]['path']} >
						{optionsUI}
					</select>
				);
			}

				defaultUI.push(<br/>);
		}
			return ( 
					<div id="section">
						<form action={this.props.action}
							method="post" commandName="configuration">
							{defaultUI}
							<input type="submit" class="btn" value="Submit" />
						</form>	
					</div>
				);
		}

	});
	
//Define the environment options 
var envArr=[];
envArr.push("serviceManagerDEV");
envArr.push("serviceManagerQA");
envArr.push("serviceManagerLIVE");

var FIELDS = [
  {label: 'Language', path: 'language', type:'input' , value: 'en'},
  {label: 'Environment', path: 'environment', type:'select' , value: envArr},
  {label: 'Locale', path: 'locale', type:'input' , value: 'US'}
];



// Render 
ReactDOM.render( <WeatherManager fields={FIELDS}/> ,
  document.getElementById('main')
);