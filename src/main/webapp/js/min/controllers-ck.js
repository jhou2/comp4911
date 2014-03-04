var cascadiaControllers=angular.module("cascadiaControllers",["base64"]);cascadiaControllers.controller("LoginController",["$scope","$http","$base64",function(e,n,a){e.login=function(){n({method:"POST",data:{username:e.username,password:e.password},url:"http://www.comp4911.com/api/user/token"}).success(function(n,s,c,l){e.response=s,e.encodedString=a.encode(n.token+":")})}}]),cascadiaControllers.controller("PackageController",["$scope",function(e){function n(n){n.selected===!0&&n.disabled!==!0&&(e.selectedPackages.push({number:n.number,title:n.title,selected:!1}),n.disabled=!0,n.selected=!1)}function a(n){if(void 0!==n&&n.selected===!0){for(var a=n.number,s=0;s<e.packages.length;s++)e.packages[s].number==a&&(e.packages[s].disabled=!1);var c=e.selectedPackages.indexOf(n);return c>-1&&e.selectedPackages.splice(c,1),!0}return!1}e.packages=[{number:"B1111",title:"Project Setup",selected:!1,disabled:!1},{number:"B1112",title:"Ongoing Updating",selected:!1,disabled:!1},{number:"B1113",title:"Detailed Planning",selected:!1,disabled:!1}],e.selectedPackages=[],e.select=function(n){e.packages[n].disabled||(e.packages[n].selected=e.packages[n].selected?!1:!0)},e.addedSelect=function(n){e.selectedPackages[n].selected=e.selectedPackages[n].selected?!1:!0},e.add=function(){e.packages.forEach(n)},e.remove=function(){for(var n=e.selectedPackages.length,s=0;n>s;)a(e.selectedPackages[s])||s++}}]),cascadiaControllers.controller("EngineerController",["$scope",function(e){function n(n){n.selected===!0&&n.disabled!==!0&&(e.selectedEngineers.push({number:n.number,name:n.name,paygrade:n.paygrade,selected:!1}),n.disabled=!0,n.selected=!1)}function a(n){if(void 0!==n&&n.selected===!0){for(var a=n.number,s=0;s<e.engineers.length;s++)e.engineers[s].number==a&&(e.engineers[s].disabled=!1);var c=e.selectedEngineers.indexOf(n);return c>-1&&e.selectedEngineers.splice(c,1),!0}return!1}e.engineers=[{number:"A1111",name:"Javier Olson",paygrade:"P4",selected:!1,disabled:!1},{number:"A1112",name:"Nancy Garcia",paygrade:"P5",selected:!1,disabled:!1},{number:"A1113",name:"David Bowden",paygrade:"SS",selected:!1,disabled:!1}],e.selectedEngineers=[],e.selectE=function(n){e.engineers[n].disabled||(e.engineers[n].selected=e.engineers[n].selected?!1:!0)},e.addedSelectE=function(n){e.selectedEngineers[n].selected=e.selectedEngineers[n].selected?!1:!0},e.addE=function(){e.engineers.forEach(n)},e.removeE=function(){for(var n=e.selectedEngineers.length,s=0;n>s;)a(e.selectedEngineers[s])||s++}}]),cascadiaControllers.controller("ManagerController",["$scope",function(e){function n(e){e.selected=!1}e.managers=[{id:"A1111",name:"Javier Olson",paygrade:"P4",selected:!1},{id:"A1112",name:"Nancy Garcia",paygrade:"P2",selected:!1},{id:"A1113",name:"David Bowden",paygrade:"P5",selected:!1},{id:"A1114",name:"Juan Burdine",paygrade:"SS",selected:!1}],e.selectedManager={},e.selectM=function(a){var s=e.managers[a].id;e.managers.forEach(n),e.managers[a].selected=!0,e.selectedManager=e.managers[a]},e.cancel=function(){e.selectedManager={},e.managers.forEach(n)}}]),cascadiaControllers.controller("UsersManagementController",["$scope","$http",function(e,n){e.users=[{employeeID:1111,name:"steven",username:"shsu",plevel:3},{employeeID:1112,name:"graeme",username:"gfunk",plevel:3},{employeeID:1113,name:"chris",username:"charris",plevel:1}],e.delete=function(n){e.users.splice(n,1),console.log("user deleted")},e.edit=function(n,a){alert(e.users[0].plevel),console.log(n)}}]);