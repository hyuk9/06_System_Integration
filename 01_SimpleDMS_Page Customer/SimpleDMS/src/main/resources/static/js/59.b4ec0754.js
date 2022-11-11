"use strict";(self["webpackChunkfront_end"]=self["webpackChunkfront_end"]||[]).push([[59],{2059:function(e,t,a){a.r(t),a.d(t,{default:function(){return p}});var n=function(){var e=this,t=e._self._c;return t("div",{staticClass:"list row"},[t("div",{staticClass:"col-md-8"},[t("div",{staticClass:"input-group mb-3"},[t("input",{directives:[{name:"model",rawName:"v-model",value:e.searchDname,expression:"searchDname"}],staticClass:"form-control",attrs:{type:"text",placeholder:"Search by Dname"},domProps:{value:e.searchDname},on:{input:function(t){t.target.composing||(e.searchDname=t.target.value)}}}),t("div",{staticClass:"input-group-append"},[t("button",{staticClass:"btn btn-outline-secondary",attrs:{type:"button"},on:{click:function(t){e.page=1,e.retrieveDept()}}},[e._v(" Search ")])])])]),t("div",{staticClass:"col-md-12"},[t("div",{staticClass:"mb-3"},[e._v(" Items per Page: "),t("select",{directives:[{name:"model",rawName:"v-model",value:e.pageSize,expression:"pageSize"}],on:{change:[function(t){var a=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.pageSize=t.target.multiple?a:a[0]},function(t){return e.handlePageSizeChange(t)}]}},e._l(e.pageSizes,(function(a){return t("option",{key:a,domProps:{value:a}},[e._v(" "+e._s(a)+" ")])})),0)]),t("b-pagination",{attrs:{"total-rows":e.count,"per-page":e.pageSize,"prev-text":"Prev","next-text":"Next"},on:{change:e.handlePageChange},model:{value:e.page,callback:function(t){e.page=t},expression:"page"}})],1),t("div",{staticClass:"col-md-6"},[t("h4",[e._v("Dept List")]),t("ul",{staticClass:"list-group",attrs:{id:"dept-list"}},e._l(e.dept,(function(a,n){return t("li",{key:n,staticClass:"list-group-item",class:{active:n==e.currentIndex},on:{click:function(t){return e.setActiveDept(a,n)}}},[e._v(" "+e._s(a.dname)+" ")])})),0),t("button",{staticClass:"m-3 btn btn-sm btn-danger",on:{click:e.removeAllDept}},[e._v(" Remove All ")])]),t("div",{staticClass:"col-md-6"},[e.currentDept?t("div",[t("h4",[e._v("Dept")]),t("div",[e._m(0),e._v(" "+e._s(e.currentDept.dname)+" ")]),t("div",[e._m(1),e._v(" "+e._s(e.currentDept.loc)+" ")]),t("router-link",{attrs:{to:"/dept/"+e.currentDept.dno}},[t("span",{staticClass:"badge bg-success"},[e._v("Edit")])])],1):t("div",[t("br"),t("p",[e._v("Please click on a Dept...")])])])])},r=[function(){var e=this,t=e._self._c;return t("label",[t("strong",[e._v("Dname:")])])},function(){var e=this,t=e._self._c;return t("label",[t("strong",[e._v("Loc:")])])}],s=a(4622),l={data(){return{dept:[],currentDept:null,currentIndex:-1,searchDname:"",page:1,count:0,pageSize:3,pageSizes:[3,6,9]}},methods:{retrieveDept(){s.Z.getAll(this.searchDname,this.page-1,this.pageSize).then((e=>{const{dept:t,totalItems:a}=e.data;this.dept=t,this.count=a,console.log(e.data)})).catch((e=>{console.log(e)}))},handlePageSizeChange(e){this.pageSize=e.target.value,this.page=1,this.retrieveDept()},handlePageChange(e){this.page=e,this.retrieveDept()},setActiveDept(e,t){this.currentDept=e,this.currentIndex=t},removeAllDept(){s.Z.deleteAll().then((e=>{console.log(e.data),this.retrieveDept(),this.currentDept=null,this.currentIndex=-1})).catch((e=>{console.log(e)}))}},mounted(){this.retrieveDept()}},i=l,c=a(1001),o=(0,c.Z)(i,n,r,!1,null,null,null),p=o.exports},4622:function(e,t,a){var n=a(6645);class r{getAll(e,t,a){return n.Z.get(`/dept?dname=${e}&page=${t}&size=${a}`)}get(e){return n.Z.get(`/dept/${e}`)}create(e){return console.log(e),n.Z.post("/dept",e)}update(e,t){return n.Z.put(`/dept/${e}`,t)}delete(e){return n.Z["delete"](`/dept/deletion/${e}`)}deleteAll(){return n.Z["delete"]("/dept/all")}}t["Z"]=new r}}]);
//# sourceMappingURL=59.b4ec0754.js.map