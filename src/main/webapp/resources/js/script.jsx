import VehiclesManager from './VehiclesManager2.jsx';	

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

var action="display-makes.html";


// Render 
ReactDOM.render( <VehiclesManager fields={FIELDS} action={action}/> ,
  document.getElementById('main')
);