(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4a4d7784"],{"454f":function(e,t,a){a("46a7");var r=a("584a").Object;e.exports=function(e,t,a){return r.defineProperty(e,t,a)}},"456d":function(e,t,a){var r=a("4bf8"),o=a("0d58");a("5eda")("keys",function(){return function(e){return o(r(e))}})},"46a7":function(e,t,a){var r=a("63b6");r(r.S+r.F*!a("8e60"),"Object",{defineProperty:a("d9f6").f})},"5eda":function(e,t,a){var r=a("5ca1"),o=a("8378"),n=a("79e5");e.exports=function(e,t){var a=(o.Object||{})[e]||Object[e],i={};i[e]=t(a),r(r.S+r.F*n(function(){a(1)}),"Object",i)}},"85f2":function(e,t,a){e.exports=a("454f")},"8e6e":function(e,t,a){var r=a("5ca1"),o=a("990b"),n=a("6821"),i=a("11e9"),l=a("f1ae");r(r.S,"Object",{getOwnPropertyDescriptors:function(e){var t,a,r=n(e),s=i.f,m=o(r),c={},u=0;while(m.length>u)a=s(r,t=m[u++]),void 0!==a&&l(c,t,a);return c}})},"990b":function(e,t,a){var r=a("9093"),o=a("2621"),n=a("cb7c"),i=a("7726").Reflect;e.exports=i&&i.ownKeys||function(e){var t=r.f(n(e)),a=o.f;return a?t.concat(a(e)):t}},ac6a:function(e,t,a){for(var r=a("cadf"),o=a("0d58"),n=a("2aba"),i=a("7726"),l=a("32e9"),s=a("84f2"),m=a("2b4c"),c=m("iterator"),u=m("toStringTag"),f=s.Array,p={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},d=o(p),b=0;b<d.length;b++){var g,h=d[b],y=p[h],D=i[h],v=D&&D.prototype;if(v&&(v[c]||l(v,c,f),v[u]||l(v,u,h),s[h]=f,y))for(g in r)v[g]||n(v,g,r[g],!0)}},bd86:function(e,t,a){"use strict";a.d(t,"a",function(){return n});var r=a("85f2"),o=a.n(r);function n(e,t,a){return t in e?o()(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}},e883:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{visible:e.dialogFormVisible,"append-to-body":""},on:{"update:visible":function(t){e.dialogFormVisible=t},closed:e.closeForm}},[a("el-form",{ref:"ruleForm",attrs:{model:e.formData,rules:e.rules,inline:!0,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"店铺名称",prop:"name"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1),a("el-form-item",{attrs:{label:"地区",prop:"area"}},[a("el-cascader",{attrs:{disabled:Boolean(e.formData.id),options:e.area},model:{value:e.formData.area,callback:function(t){e.$set(e.formData,"area",t)},expression:"formData.area"}})],1),a("el-form-item",{attrs:{label:"详细地址",prop:"addr"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off"},model:{value:e.formData.addr,callback:function(t){e.$set(e.formData,"addr",t)},expression:"formData.addr"}})],1),a("el-form-item",{attrs:{label:"联系电话",prop:"phone"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off"},model:{value:e.formData.phone,callback:function(t){e.$set(e.formData,"phone",t)},expression:"formData.phone"}})],1),a("el-form-item",{attrs:{label:"开业时间",prop:"createTime"}},[a("el-date-picker",{attrs:{"value-format":"timestamp",type:"date",placeholder:"选择日期"},model:{value:e.formData.createTime,callback:function(t){e.$set(e.formData,"createTime",t)},expression:"formData.createTime"}})],1),a("el-form-item",{attrs:{label:"店铺状态",prop:"state"}},[a("el-select",{model:{value:e.formData.state,callback:function(t){e.$set(e.formData,"state",t)},expression:"formData.state"}},[a("el-option",{attrs:{label:"营业中",value:1}}),a("el-option",{attrs:{label:"未营业",value:2}})],1)],1),a("el-form-item",{attrs:{label:"备注",prop:"remarks"}},[a("el-input",{attrs:{type:"textarea",maxlength:"20",autocomplete:"off"},model:{value:e.formData.remarks,callback:function(t){e.$set(e.formData,"remarks",t)},expression:"formData.remarks"}})],1),a("el-form-item",{attrs:{label:"用户账号",prop:"username"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off",disabled:Boolean(e.formData.id)},model:{value:e.formData.username,callback:function(t){e.$set(e.formData,"username",t)},expression:"formData.username"}})],1),a("el-form-item",{attrs:{label:"用户密码",prop:"password"}},[a("el-input",{attrs:{type:"password",autocomplete:"off",clearable:""},model:{value:e.formData.password,callback:function(t){e.$set(e.formData,"password",t)},expression:"formData.password"}})],1),a("el-form-item",{attrs:{label:"用户姓名",prop:"uname"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off"},model:{value:e.formData.uname,callback:function(t){e.$set(e.formData,"uname",t)},expression:"formData.uname"}})],1),a("el-form-item",{attrs:{label:"用户联系电话",prop:"uphone"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off"},model:{value:e.formData.uphone,callback:function(t){e.$set(e.formData,"uphone",t)},expression:"formData.uphone"}})],1),a("el-form-item",{attrs:{label:"用户性别",prop:"sex"}},[a("el-select",{model:{value:e.formData.sex,callback:function(t){e.$set(e.formData,"sex",t)},expression:"formData.sex"}},[a("el-option",{attrs:{label:"男",value:1}}),a("el-option",{attrs:{label:"女",value:2}})],1)],1),a("el-form-item",{attrs:{label:"用户年龄",prop:"age"}},[a("el-input",{attrs:{type:"text",maxlength:"20",autocomplete:"off"},model:{value:e.formData.age,callback:function(t){e.$set(e.formData,"age",t)},expression:"formData.age"}})],1),a("el-form-item",{attrs:{label:"用户入职日期",prop:"entryTime"}},[a("el-date-picker",{attrs:{"value-format":"timestamp",type:"date",placeholder:"选择日期"},model:{value:e.formData.entryTime,callback:function(t){e.$set(e.formData,"entryTime",t)},expression:"formData.entryTime"}})],1),a("el-form-item",{attrs:{label:"用户生日",prop:"birthday"}},[a("el-date-picker",{attrs:{"value-format":"timestamp",type:"date",placeholder:"选择日期"},model:{value:e.formData.birthday,callback:function(t){e.$set(e.formData,"birthday",t)},expression:"formData.birthday"}})],1),a("el-form-item",{attrs:{label:"用户备注",prop:"uremarks"}},[a("el-input",{attrs:{type:"textarea",maxlength:"20",autocomplete:"off"},model:{value:e.formData.uremarks,callback:function(t){e.$set(e.formData,"uremarks",t)},expression:"formData.uremarks"}})],1)],1),a("el-tree",{ref:"tree",attrs:{data:e.accessList,"show-checkbox":"","default-expand-all":"","node-key":"id","highlight-current":"",props:{children:"children",label:"name"}}}),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submit("ruleForm")}}},[e._v("确 定")])],1)],1),a("div",{staticClass:"top"},[a("el-form",{attrs:{inline:!0,model:e.searchForm,size:"small"}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"输入店铺名称查询",type:"text"},model:{value:e.searchForm.name,callback:function(t){e.$set(e.searchForm,"name",t)},expression:"searchForm.name"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.fetch()}}},[e._v("查询")]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.reset()}}},[e._v("重置")])],1)],1),-1!==e.$store.state.permission.indexOf(101)?a("el-button",{attrs:{type:"primary",size:"small",round:""},on:{click:function(t){e.dialogFormVisible=!0}}},[e._v("新建\n    ")]):e._e()],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.tableData}},[a("el-table-column",{attrs:{prop:"id",label:"编号"}}),a("el-table-column",{attrs:{prop:"name",label:"分店名称"}}),a("el-table-column",{attrs:{prop:"phone",label:"联系电话"}}),a("el-table-column",{attrs:{prop:"type",label:"类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(1===t.row.type?"总店":"分店"))])]}}])}),a("el-table-column",{attrs:{prop:"provice",label:"地区"}}),a("el-table-column",{attrs:{prop:"addr",label:"地址"}}),a("el-table-column",{attrs:{prop:"createTime",label:"开业时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.formatDate(new Date(t.row.createTime.time),"yyyy-MM-dd")))])]}}])}),a("el-table-column",{attrs:{prop:"remarks",label:"备注"}}),a("el-table-column",{attrs:{prop:"state",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(1===t.row.state?"营业中":"未营业"))])]}}])}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{attrs:{"current-page":e.pagination.current,layout:"total, prev, pager, next, jumper",total:e.pagination.total},on:{"current-change":e.fetch}})],1)},o=[],n=(a("8e6e"),a("ac6a"),a("456d"),a("7f7f"),a("bd86")),i=a("f4e6");function l(e,t){var a=Object.keys(e);return Object.getOwnPropertySymbols&&a.push.apply(a,Object.getOwnPropertySymbols(e)),t&&(a=a.filter(function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable})),a}function s(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?l(a,!0).forEach(function(t){Object(n["a"])(e,t,a[t])}):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):l(a).forEach(function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))})}return e}var m={name:"Mall",mixins:[i["b"]],data:function(){var e=function(e,t,a){var r=/^[1][3,4,5,6,7,8,9][0-9]{9}$/;r.test(t)?a():a(new Error("请输入正确的手机号"))},t=function(e,t,a){var r=/^[a-zA-Z0-9_-]{6,12}$/;r.test(t)?a():a(new Error("请输入6到12位正确的用户名"))};return{tableData:[],area:i["a"],pagination:{},loading:!1,searchForm:{},dialogFormVisible:!1,formData:{},rules:{phone:[{required:!0,message:"请输入内容",trigger:"blur"},{validator:e,trigger:"blur"}],birthday:[{required:!0,message:"请输入内容",trigger:"blur"}],sex:[{required:!0,message:"请输入内容",trigger:"blur"}],name:[{required:!0,message:"请输入内容",trigger:"blur"}],type:[{required:!0,message:"请输入内容",trigger:"blur"}],area:[{required:!0,message:"请输入内容",trigger:"blur"}],addr:[{required:!0,message:"请输入内容",trigger:"blur"}],createTime:[{required:!0,message:"请输入内容",trigger:"blur"}],state:[{required:!0,message:"请输入内容",trigger:"blur"}],username:[{required:!0,message:"请输入内容",trigger:"blur"},{validator:t,trigger:"blur"}],uname:[{required:!0,message:"请输入内容",trigger:"blur"}],uphone:[{required:!0,message:"请输入内容",trigger:"blur"},{validator:e,trigger:"blur"}],age:[{required:!0,message:"请输入内容",trigger:"blur"}],entryTime:[{required:!0,message:"请输入内容",trigger:"blur"}]},accessList:JSON.parse(sessionStorage.getItem("access"))}},methods:{reset:function(){this.searchForm={},this.fetch()},closeForm:function(){this.formData={},this.$refs.tree.setCheckedKeys([])},handleEdit:function(e){var t=this;this.$ajax.post("/showUpdateShop.action",{shopId:e.id}).then(function(a){1===a.data.code&&(t.dialogFormVisible=!0,t.$nextTick(function(){for(var r=[],o=0;o<a.data.PermissionVolist.length;o++)r.push(a.data.PermissionVolist[o].id);t.$refs.tree.setCheckedKeys([].concat(r)),t.formData=JSON.parse(JSON.stringify(s({},e,{createTime:e.createTime.time,area:[e.provid+"",e.cityid+"",e.areaid+""],uId:a.data.userVo2.id,username:a.data.userVo2.username,uname:a.data.userVo2.name,uphone:a.data.userVo2.phone,sex:a.data.userVo2.sex,age:a.data.userVo2.age,entryTime:new Date("2019-07-19 11:47:18").getTime(),birthday:new Date(a.data.userVo2.birthday).getTime(),uremarks:a.data.userVo2.remarks,password:a.data.userVo2.password})))}))})},submit:function(e){var t=this;this.$refs[e].validate(function(e){if(e)return console.log(t.$refs.tree.getCheckedKeys()),t.$ajax.post(t.formData.id?"/updateShop.action":"/insertShop.action",s({},t.formData,{createTime:t.formatDate(new Date(t.formData.createTime),"yyyy-MM-dd hh:mm:ss.S"),entryTime:t.formatDate(new Date(t.formData.entryTime),"yyyy-MM-dd hh:mm:ss.S"),birthday:t.formatDate(new Date(t.formData.birthday),"yyyy-MM-dd hh:mm:ss.S"),provid:t.formData.area[0],cityid:t.formData.area[1],areaid:t.formData.area[2],type:2,ids:t.$refs.tree.getCheckedKeys().join(",")})).then(function(e){1===e.data.code&&(t.dialogFormVisible=!1,t.$message.success(e.data.msg),t.fetch(t.pagination.current))}),!1})},fetch:function(e){var t=this;this.loading=!0,this.$ajax.post("/listByShopNameVo.action",s({limit:10,page:e||1},this.searchForm)).then(function(a){if(1===a.data.code){var r=s({},t.pagination);r.total=a.data.count,r.current=e,t.loading=!1,t.tableData=a.data.data,t.pagination=r}})},handleDelete:function(e){var t=this;this.$confirm("是否删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$ajax.post("/deleteShop.action",{id:e.id}).then(function(e){1===e.data.code&&(t.$message.success(e.data.msg),t.fetch(t.pagination.current))})}).catch(function(){t.$message.info("已取消")})}},mounted:function(){this.fetch()}},c=m,u=a("2877"),f=Object(u["a"])(c,r,o,!1,null,"f42218c8",null);t["default"]=f.exports},f1ae:function(e,t,a){"use strict";var r=a("86cc"),o=a("4630");e.exports=function(e,t,a){t in e?r.f(e,t,o(0,a)):e[t]=a}}}]);