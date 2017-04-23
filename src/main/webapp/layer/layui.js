/** layui-v0.0.3 跨设备模块化前端框架@LGPL http://www.layui.com/ By 贤心 */
;!function(e){var t=function(){this.v="0.0.3"};t.fn=t.prototype;var n=document,o={},l=t.fn.cache={},i=function(){var e=n.scripts,t=e[e.length-1].src;return t.substring(0,t.lastIndexOf("/")+1)}(),r=function(t){e.console&&console.error&&console.error("layui Error: "+t)},u="undefined"!=typeof opera&&"[object Opera]"===opera.toString();l.device="pc",l.modules={},l.status={},l.timeout=10,t.fn.define=function(e,t){var n=this,o="function"==typeof e;return o&&(t=e),n.use(o?[]:e,function(){"function"==typeof t&&t(function(e,t){layui[e]=t,l.status[e]=!0})}),n},t.fn.use=function(e,t,a){function s(e,t){var n="PLAYSTATION 3"===navigator.platform?/^complete$/:/^(complete|loaded)$/;("load"===e.type||n.test((e.currentTarget||e.srcElement).readyState))&&(l.modules[p]=t,m.removeChild(v),function o(){return++y>1e3*l.timeout/4?r(p+" is not a valid module"):void(l.status[p]?d():setTimeout(o,4))}())}function d(){a.push(layui[p]),e.length>1?c.use(e.slice(1),t,a):"function"==typeof t&&t.apply(layui,a)}var c=this,f=l.dir=l.dir?l.dir:i,m=n.getElementsByTagName("head")[0];e="string"==typeof e?[e]:e,l.extend_dir=f+"lay/"+l.device+"/extend/";var p=e[0],y=0;if(a=a||[],l.host=l.host||(f.match(/\/\/([\s\S]+?)\//)||["//"+location.host+"/"])[0],0===e.length)return t();var v=n.createElement("script"),g=(o.modules[p]?f+"lay/":l.base||"")+(c.modules[p]||p)+".js";return v.async=!0,v.src=g+"?v="+function(){return l.version===!0?l.v||(new Date).getTime():l.version||""}(),l.modules[p]?!function h(){return++y>1e3*l.timeout/4?r(p+" is not a valid module"):void("string"==typeof l.modules[p]&&l.status[p]?d():setTimeout(h,4))}():(m.appendChild(v),!v.attachEvent||v.attachEvent.toString&&v.attachEvent.toString().indexOf("[native code")<0||u?v.addEventListener("load",function(e){s(e,g)},!1):v.attachEvent("onreadystatechange",function(e){s(e,g)})),l.modules[p]=g,c},t.fn.all=function(e){var t=this,n=Object.keys?Object.keys(t.modules):function(){var e=[];for(var n in t.modules)e.push(n);return e}();return layui.use(n,e),t},t.fn.getStyle=function(t,n){var o=t.currentStyle?t.currentStyle:e.getComputedStyle(t,null);return o[o.getPropertyValue?"getPropertyValue":"getAttribute"](n)},t.fn.link=function(e,t,o){var i=this,u=n.createElement("link"),a=n.getElementsByTagName("head")[0];"string"==typeof t&&(o=t);var s=(o||e).replace(/\.|\//g,""),d=u.id="layuicss-"+s,c=0;u.rel="stylesheet",u.href=e+(l.debug?"?v="+(new Date).getTime():""),u.media="all",n.getElementById(d)||a.appendChild(u),"function"==typeof t&&!function f(){return++c>1e3*l.timeout/100?r(e+" timeout"):void(1989===parseInt(i.getStyle(n.getElementById(d),"width"))?function(){t()}():setTimeout(f,100))}()},t.fn.addcss=function(e,t,n){layui.link(l.dir+"css/"+e,t,n)},t.fn.router=function(e){for(var t,n=(e||location.hash).replace(/^#/,"").split("/")||[],o={dir:[]},l=0;l<n.length;l++)t=n[l].split("="),/^\w+=/.test(n[l])?function(){"dir"!==t[0]&&(o[t[0]]=t[1])}():o.dir.push(n[l]),t=null;return o},t.fn.img=function(e,t,n){var o=new Image;o.onload=function(){o.onload=null,t&&t(o)},o.onerror=function(e){o.onload=null,n&&n(e)},o.src=e},t.fn.each=function(e,t){if("function"==typeof t)if(e.constructor===Array)for(var n=0;n<e.length&&!t(n,e[n]);n++);else if(e.constructor===Object)for(var o in e)if(t(o,e[o]))break},t.fn.config=function(e){e=e||{};for(var t in e)l[t]=e[t];return this},o.modules={laytpl:"lib/laytpl",laypage:"lib/laypage",terminal:{pc:{jquery:"pc/lib/jquery",layout:"pc/modules/layout",layer:"pc/modules/layer",laydate:"pc/modules/laydate",layim:"pc/modules/layim",tree:"pc/modules/tree",slide:"pc/modules/slide",editor:"pc/modules/editor",table:"pc/modules/table",flow:"pc/modules/flow",util:"pc/modules/util",form:"pc/modules/form",code:"pc/modules/code",upload:"pc/modules/upload",single:"pc/modules/single"},mobile:{zepto:"mobile/lib/zepto",layer:"mobile/modules/layer",util:"mobile/modules/util",ui:"mobile/modules/ui",form:"mobile/modules/form",debug:"mobile/modules/debug"}}},t.fn.modules=function(){var e={};for(var t in o.modules)if("terminal"===t){var n=o.modules.terminal[l.device];for(var i in n)e[i]=n[i],o.modules[i]=n[i];delete o.modules.terminal}else e[t]=o.modules[t];return e}(),t.fn.extend=function(e){var t=this;e=e||{};for(var n in e)t[n]||t.modules[n]?r("模块名 "+n+" 已被占用"):t.modules[n]=e[n];return t},e.layui=new t}(window);