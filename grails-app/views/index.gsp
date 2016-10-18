<!DOCTYPE HTML>
<html ng-app="app" ng-controller="Header as main">
	<head> 
		<title ng-bind="title | translate" ng-cloak class="ng-cloak"></title>
		<base href="/"></base>
		<!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.10/angular.min.js"></script>-->
	    <link id="wlfavicon" rel="shortcut icon" ng-href="{{main.favicon}}">
	    <!-- ================== BEGIN BASE CSS STYLE ================== -->
	    <link href='//fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
	    <link href='//fonts.googleapis.com/css?family=Signika:600' rel='stylesheet' type='text/css'>
	    <asset:stylesheet src="manifest.css"/>
	    <!-- ================== END BASE CSS STYLE ================== -->
	</head>
	<body ng-class="{'has-overlay': main.getOverlayPage()}" wl-app-init="" color-set="main.app.colorSet">
		<!-- begin #page-loader -->
	    <div id="page-loader" class="fade in" ng-class="{ out: main.appLoaded }"><span class="spinner"></span>
	    </div>
	    <!-- end #page-loader -->

	    <!-- begin #page-container -->
	    <div id="page-container" class="fade" ng-class="{ in: main.appLoaded }">
			<!-- begin:header -->
			<!--<div ng-include src="'tpls/header.html'" ng-if="main.header">yess</div>-->
			<div ng-include src="'assets/app/layout/header/header.html'" ng-if="main.header"></div>
			<!-- end:header -->
			<!-- begin:sidebar -->
			<div ng-include src="'/assets/app/layout/sidebar/sidebar.html'" ng-if="main.sidebar"></div>
			<!-- end:sidebar -->

			<!-- begin #templates -->
			<div ng-if="main.domainExists">
        		<div ng-include src="'assets/app/layout/header/notification/resolvables.html'" 
        		ng-controller="ResolvableNotification as resolvable" ng-if="main.sidebar"></div>
				<div class="ng-view"></div>
				<div ng-include src="'assets/app/user/overlay/overlay.html'" ng-if="main.header && main.sidebar"></div>
			</div>
			<!-- end #templates -->

			<!-- begin #non-existent-tenant-domain-error -->
			<div id="content" class="content ng-cloak" ng-if="!main.domainExists" ng-cloak ng-bind="main.serverErrorCode | translate">
			</div>
			<!-- end #error -->
			<div ng-include src="'assets/app/layout/footer/footer.html'"></div>
	    </div>
	    <!-- end page container -->


	    <!-- ================== BEGIN BASE JS ================== -->
		<!--[if lt IE 9]>
			<asset:javascript src="scripts/crossbrowserjs/html5shiv.js"/>
			<asset:javascript src="scripts/crossbrowserjs/respond.min.js"/>
			<asset:javascript src="scripts/crossbrowserjs/excanvas.min.js"/>
		<![endif]-->
		<!-- ================== END BASE JS ================== -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<asset:javascript src="app/manifest.js" />
		<!-- ================== END PAGE LEVEL JS ================== -->

        <!-- Start Beacon -->
        %{--<script>!function(e,o,n){window.HSCW=o,window.HS=n,n.beacon=n.beacon||{};var t=n.beacon;t.userConfig={},t.readyQueue=[],t.config=function(e){this.userConfig=e},t.ready=function(e){this.readyQueue.push(e)},o.config={docs:{enabled:!0,baseUrl:"//monopond.helpscoutdocs.com/"},contact:{enabled:!0,formId:"3eff87a5-07a4-11e6-a329-0ee2467769ff"}};var r=e.getElementsByTagName("script")[0],c=e.createElement("script");c.type="text/javascript",c.async=!0,c.src="https://djtflbt20bdde.cloudfront.net/",r.parentNode.insertBefore(c,r)}(document,window.HSCW||{},window.HS||{});</script>--}%
        <!-- End Beacon -->
    </body>
</html>