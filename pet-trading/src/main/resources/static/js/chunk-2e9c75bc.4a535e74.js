(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2e9c75bc"],{"1dde":function(n,e,i){var t=i("d039"),d=i("b622"),s=i("2d00"),a=d("species");n.exports=function(n){return s>=51||!t((function(){var e=[],i=e.constructor={};return i[a]=function(){return{foo:1}},1!==e[n](Boolean).foo}))}},"3dac":function(n,e,i){},6265:function(n,e,i){"use strict";i.r(e);var t=function(){var n=this,e=n.$createElement,i=n._self._c||e;return i("div",{staticClass:"petkindmanage"},[i("div",{staticClass:"bkindbar"},[i("div",{staticClass:"selectbar"},[i("el-form",{attrs:{inline:!0,model:n.bkindform}},[i("el-form-item",{attrs:{label:"类型id"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"类型id"},model:{value:n.bkindform.bkid,callback:function(e){n.$set(n.bkindform,"bkid",e)},expression:"bkindform.bkid"}})],1),i("el-form-item",{attrs:{label:"类型名称"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"类型名称"},model:{value:n.bkindform.bkindname,callback:function(e){n.$set(n.bkindform,"bkindname",e)},expression:"bkindform.bkindname"}})],1),i("el-form-item",[i("el-button",{attrs:{clearable:"",icon:"el-icon-search",type:"primary",circle:""},on:{click:n.querybkind}})],1)],1),i("el-form",{attrs:{inline:!0,model:n.bkindaddform}},[i("el-form-item",{attrs:{label:"类型名称"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"类型名称"},model:{value:n.bkindaddform.bkindname,callback:function(e){n.$set(n.bkindaddform,"bkindname",e)},expression:"bkindaddform.bkindname"}})],1),i("el-form-item",[i("el-button",{attrs:{clearable:"",icon:"el-icon-plus",type:"primary",circle:""},on:{click:n.addbkind}})],1)],1)],1),i("div",{staticClass:"bkind"},[i("el-table",{staticStyle:{width:"541px"},attrs:{data:n.bkind,border:""}},[i("el-table-column",{attrs:{prop:"bkid",label:"类型id",sortable:"",width:"180"}}),i("el-table-column",{attrs:{label:"类型名称",width:"180"},scopedSlots:n._u([{key:"default",fn:function(e){return[n.showbkindindex!=e.$index?i("span",[n._v(n._s(e.row.bkindname))]):n._e(),n.showbkindindex==e.$index?i("el-input",{attrs:{clearable:"",type:"primary"},model:{value:n.upbkind.bkindname,callback:function(e){n.$set(n.upbkind,"bkindname",e)},expression:"upbkind.bkindname"}}):n._e()]}}])}),i("el-table-column",{attrs:{label:"操作",width:"180"},scopedSlots:n._u([{key:"default",fn:function(e){return[n.showbkindindex==e.$index?i("span",[i("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(i){return n.updatebkind(e.$index,e.row)}}},[n._v("确认")]),i("el-button",{attrs:{size:"mini",type:"info"},on:{click:function(e){n.showbkindindex=-1}}},[n._v("取消")])],1):n._e(),n.showbkindindex!=e.$index?i("span",[i("el-button",{attrs:{size:"mini"},on:{click:function(i){return n.handleEditbkind(e.$index,e.row)}}},[n._v("编辑")]),i("el-popconfirm",{attrs:{confirmButtonText:"确定",cancelButtonText:"取消",placement:"top",icon:"el-icon-info",iconColor:"red",title:"确定删除该用户吗？"},on:{onConfirm:function(i){return n.handleDeletebkind(e.$index,e.row)}}},[n.showbkindindex!=e.index?i("el-button",{staticStyle:{"margin-left":"10px"},attrs:{slot:"reference",size:"mini",type:"danger"},slot:"reference"},[n._v("删除")]):n._e()],1)],1):n._e()]}}])})],1)],1)]),i("div",{staticClass:"skindbar"},[i("div",{staticClass:"selectbar"},[i("el-form",{attrs:{inline:!0,model:n.skindform}},[i("el-form-item",{attrs:{label:"品种id"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"品种id"},model:{value:n.skindform.skid,callback:function(e){n.$set(n.skindform,"skid",e)},expression:"skindform.skid"}})],1),i("el-form-item",{attrs:{label:"对应类型id"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"类型id"},model:{value:n.skindform.bkid,callback:function(e){n.$set(n.skindform,"bkid",e)},expression:"skindform.bkid"}})],1),i("el-form-item",{attrs:{label:"品种名称"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"品种名称"},model:{value:n.skindform.skindname,callback:function(e){n.$set(n.skindform,"skindname",e)},expression:"skindform.skindname"}})],1),i("el-form-item",[i("el-button",{attrs:{clearable:"",icon:"el-icon-search",type:"primary",circle:""},on:{click:n.queryskind}})],1)],1),i("el-form",{attrs:{inline:!0,model:n.skindaddform}},[i("el-form-item",{attrs:{label:"对应类型id"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"类型id"},model:{value:n.skindaddform.bkid,callback:function(e){n.$set(n.skindaddform,"bkid",e)},expression:"skindaddform.bkid"}})],1),i("el-form-item",{attrs:{label:"品种名称"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{clearable:"",placeholder:"品种名称"},model:{value:n.skindaddform.skindname,callback:function(e){n.$set(n.skindaddform,"skindname",e)},expression:"skindaddform.skindname"}})],1),i("el-form-item",[i("el-button",{attrs:{clearable:"",icon:"el-icon-plus",type:"primary",circle:""},on:{click:n.addskind}})],1)],1)],1),i("div",{staticClass:"skind"},[i("el-table",{staticStyle:{width:"721px"},attrs:{data:n.skind,border:""}},[i("el-table-column",{attrs:{prop:"skid",label:"品种id",sortable:"",width:"180"}}),i("el-table-column",{attrs:{label:"对应类型id",width:"180"},scopedSlots:n._u([{key:"default",fn:function(e){return[n.showskindindex!=e.$index?i("span",[n._v(n._s(e.row.bkid))]):n._e(),n.showskindindex==e.$index?i("el-input",{attrs:{clearable:"",type:"primary"},model:{value:n.upskind.bkid,callback:function(e){n.$set(n.upskind,"bkid",e)},expression:"upskind.bkid"}}):n._e()]}}])}),i("el-table-column",{attrs:{label:"品种名称",width:"180"},scopedSlots:n._u([{key:"default",fn:function(e){return[n.showskindindex!=e.$index?i("span",[n._v(n._s(e.row.skindname))]):n._e(),n.showskindindex==e.$index?i("el-input",{attrs:{clearable:"",type:"primary"},model:{value:n.upskind.skindname,callback:function(e){n.$set(n.upskind,"skindname",e)},expression:"upskind.skindname"}}):n._e()]}}])}),i("el-table-column",{attrs:{label:"操作",width:"180"},scopedSlots:n._u([{key:"default",fn:function(e){return[n.showskindindex==e.$index?i("span",[i("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(i){return n.updateskind(e.$index,e.row)}}},[n._v("确认")]),i("el-button",{attrs:{size:"mini",type:"info"},on:{click:function(e){n.showskindindex=-1}}},[n._v("取消")])],1):n._e(),n.showskindindex!=e.$index?i("span",[i("el-button",{attrs:{size:"mini"},on:{click:function(i){return n.handleEditskind(e.$index,e.row)}}},[n._v("编辑")]),i("el-popconfirm",{attrs:{confirmButtonText:"确定",cancelButtonText:"取消",placement:"top",icon:"el-icon-info",iconColor:"red",title:"确定删除该用户吗？"},on:{onConfirm:function(i){return n.handleDeleteskind(e.$index,e.row)}}},[i("el-button",{staticStyle:{"margin-left":"10px"},attrs:{slot:"reference",size:"mini",type:"danger"},slot:"reference"},[n._v("删除")])],1)],1):n._e()]}}])})],1)],1)])])},d=[],s=(i("e9c4"),i("a434"),i("62ad")),a=i("a0d5"),o={name:"petkindmanage",data:function(){return{showbkindindex:-1,showskindindex:-1,upbkind:{},upskind:{},bkind:[],skind:[],bkindform:{},bkindaddform:{},skindform:{},skindaddform:{}}},created:function(){var n=this;Object(s["d"])().then((function(e){n.bkind=e})).catch((function(n){console.log(n)})),Object(a["c"])().then((function(e){n.skind=e})).catch((function(n){console.log(n)}))},methods:{querybkind:function(){var n=this;Object(s["d"])({bkid:this.bkindform.bkid,bkindname:this.bkindform.bkindname}).then((function(e){n.bkind=e})).catch((function(n){console.log(n)}))},addbkind:function(){var n=this;Object(s["a"])({bkindname:this.bkindaddform.bkindname}).then((function(e){n.$notify({title:"添加成功",message:e.msg,type:"success",offset:100}),n.bkindaddform.bkid=e.bkid;var i=JSON.stringify(n.bkindaddform);n.bkind.push(JSON.parse(i))})).catch((function(n){console.log(n)}))},queryskind:function(){var n=this;Object(a["c"])({skid:this.skindform.skid,bkid:this.skindform.bkid,skindname:this.skindform.skindname}).then((function(e){n.skind=e})).catch((function(n){console.log(n)}))},addskind:function(){var n=this;Object(a["a"])({bkid:this.skindaddform.bkid,skindname:this.skindaddform.skindname}).then((function(e){n.$notify({title:"添加成功",message:e.msg,type:"success",offset:100}),n.skindaddform.skid=e.skid;var i=JSON.stringify(n.skindaddform);n.skind.push(JSON.parse(i))})).catch((function(n){console.log(n)}))},handleEditbkind:function(n,e){this.showbkindindex=n;var i=JSON.stringify(e);this.upbkind=JSON.parse(i)},updatebkind:function(){var n=this;Object(s["e"])({bkid:this.upbkind.bkid,bkindname:this.upbkind.bkindname}).then((function(e){n.$notify({title:"修改成功",message:e,type:"success",offset:100}),n.bkind[n.showbkindindex].bkindname=n.upbkind.bkindname,n.showbkindindex=-1})).catch((function(n){console.log(n)}))},handleDeletebkind:function(n,e){var i=this;Object(s["b"])({bkid:e.bkid}).then((function(e){i.$notify({title:"删除成功",message:e,type:"success",offset:100}),i.bkind.splice(n,1)})).catch((function(n){console.log(n)}))},handleEditskind:function(n,e){this.showskindindex=n;var i=JSON.stringify(e);this.upskind=JSON.parse(i)},updateskind:function(){var n=this;Object(a["d"])({skid:this.upskind.skid,bkid:this.upskind.bkid,skindname:this.upskind.skindname}).then((function(e){n.$notify({title:"修改成功",message:e,type:"success",offset:100}),n.skind[n.showskindindex].bkid=n.upskind.bkid,n.skind[n.showskindindex].skindname=n.upskind.skindname,n.showskindindex=-1})).catch((function(n){console.log(n)}))},handleDeleteskind:function(n,e){var i=this;Object(a["b"])({skid:e.skid}).then((function(e){i.$notify({title:"删除成功",message:e,type:"success",offset:100}),i.skind.splice(n,1)})).catch((function(n){console.log(n)}))}}},r=o,l=(i("7924"),i("2877")),c=Object(l["a"])(r,t,d,!1,null,"1c20e1e7",null);e["default"]=c.exports},"62ad":function(n,e,i){"use strict";i.d(e,"c",(function(){return d})),i.d(e,"d",(function(){return s})),i.d(e,"a",(function(){return a})),i.d(e,"b",(function(){return o})),i.d(e,"e",(function(){return r}));var t=i("1bab");function d(){return Object(t["a"])({url:"/bkind/queryBKind"})}function s(n){return Object(t["a"])({url:"/bkind/queryBKindandSKind",params:n})}function a(n){return Object(t["a"])({url:"/bkind/addBKind",method:"post",params:n})}function o(n){return Object(t["a"])({url:"/bkind/deleteBKind",params:n})}function r(n){return Object(t["a"])({url:"/bkind/updateBKind",params:n})}},7924:function(n,e,i){"use strict";i("3dac")},8418:function(n,e,i){"use strict";var t=i("a04b"),d=i("9bf2"),s=i("5c6c");n.exports=function(n,e,i){var a=t(e);a in n?d.f(n,a,s(0,i)):n[a]=i}},a0d5:function(n,e,i){"use strict";i.d(e,"c",(function(){return d})),i.d(e,"a",(function(){return s})),i.d(e,"b",(function(){return a})),i.d(e,"d",(function(){return o}));var t=i("1bab");function d(n){return Object(t["a"])({url:"/skind/queryallskind",params:n})}function s(n){return Object(t["a"])({url:"/skind/addskind",method:"post",params:n})}function a(n){return Object(t["a"])({url:"/skind/deleteskind",params:n})}function o(n){return Object(t["a"])({url:"/skind/updateskind",method:"post",params:n})}},a434:function(n,e,i){"use strict";var t=i("23e7"),d=i("da84"),s=i("23cb"),a=i("5926"),o=i("07fa"),r=i("7b0b"),l=i("65f0"),c=i("8418"),k=i("1dde"),u=k("splice"),b=d.TypeError,f=Math.max,m=Math.min,p=9007199254740991,h="Maximum allowed length exceeded";t({target:"Array",proto:!0,forced:!u},{splice:function(n,e){var i,t,d,k,u,x,y=r(this),w=o(y),v=s(n,w),$=arguments.length;if(0===$?i=t=0:1===$?(i=0,t=w-v):(i=$-2,t=m(f(a(e),0),w-v)),w+i-t>p)throw b(h);for(d=l(y,t),k=0;k<t;k++)u=v+k,u in y&&c(d,k,y[u]);if(d.length=t,i<t){for(k=v;k<w-t;k++)u=k+t,x=k+i,u in y?y[x]=y[u]:delete y[x];for(k=w;k>w-t+i;k--)delete y[k-1]}else if(i>t)for(k=w-t;k>v;k--)u=k+t-1,x=k+i-1,u in y?y[x]=y[u]:delete y[x];for(k=0;k<i;k++)y[k+v]=arguments[k+2];return y.length=w-t+i,d}})}}]);
//# sourceMappingURL=chunk-2e9c75bc.4a535e74.js.map