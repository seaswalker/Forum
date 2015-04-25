    //全局数据
    var section = {
        sid : 0,
        section_btns : null
    };

    $(function() {
        //选项卡显示/隐藏
        _init_tab();
        //切换右侧面板
        _init_panel_listener();
    });

    /**
     * [_init_tab 初始化左侧选项tab切换效果]
     */
    function _init_tab() {
        $(".tab").click(function() {
          $(this).next().toggle();
        });
    }

    /**
     * [_init_panel_listener 初始化功能面板切换]
     */
    function _init_panel_listener() {
        var divs = $(".r_d").children("div");
        $(".tab_detail").children("li").click(function() {
          var id = $(this).attr("name");
          divs.each(function() {
            if($(this).attr("id") == id) {
              $(this).show();
              if(id == "s_l") {
                //加载板块列表面板
               _init_section_list_panel();
              }
            }else {
              $(this).hide();
            }
          });
        });
    }

    /**
     * [_init_section_list 初始化板块列表面板]
     */
    function _init_section_list_panel() {
        //渲染板块树
        _load_section_tree();
        _reset_section_ops();
        //按钮全部变为不可用
        if(section.section_btns == null) {
            section.section_btns = $(".btns").children("button");
        }
        section.section_btns.attr("disabled", true);
    }
        
      /**
       * [_load_section_tree 加载板块树]
       * #tree 树载体
       * @return {[type]} [void]
       */
      function _load_section_tree() {
        var url = "admin/section/list.html";
        var tree = $("#tree");
        tree.html("正在努力加载...");
        $.post(
            url,
            function(data) {
                //顶级li，包围一个顶级板块及其子版块
                var top_li;
                //子版块所依附的ul
                var sec_ul;
                //子版块的li
                var sec_li;
                var span;
                tree.empty();
                $.each(data, function(index, section) {
                    //如果是顶级板块
                    if(section.pid == "0") {
                        top_li = $("<li></li>");
                        span = $("<span class='folder leaf'></span>");
                        span.html(section.name);
                        span.attr('id', section.id);
                        span.get(0).setAttribute("old_manager", section.manager == undefined ? '' : section.manager);
                        top_li.append(span);
                        sec_ul = $("<ul></ul>");
                        top_li.append(sec_ul);
                        tree.append(top_li);
                    }else {
                        sec_li = $("<li></li>");
                        span = $("<span class='leaf'></span>");
                        span.attr('id', section.id);
                        span.get(0).setAttribute("old_manager", section.manager == undefined ? '' : section.manager);
                        span.html(section.name);
                        sec_li.append(span);
                        sec_ul.append(sec_li);
                    }
                });
            //渲染treeview
            tree.treeview();
            //增加列表事件监听
            _init_section_list_listener();
            //初始化板块操作按钮监听
            _init_section_operation_btns();
            },
        "json"
        );
      }

      /**
       * [_init_section_list_listener 初始化板块列表点击事件监听]
       */
      function _init_section_list_listener() {
        //属性菜单添加选中样式，并且控制板块按钮的可用性
        var tree_lis = $(".leaf");
        tree_lis.click(function() {
            tree_lis.removeClass("tree_li_active");
            $(this).addClass("tree_li_active");
            //为sid赋值
            section.sid = $(this).attr("id");
            for(var i = 0;i < section.section_btns.length;i ++) {
                section.section_btns.get(i).removeAttribute("disabled");
            }
            if(!$(this).hasClass("folder")) {
                $(section.section_btns.get(0)).attr("disabled", true);
                //如果尚未设置版主，那么删除版主不可用
                var old_manager = this.getAttribute("old_manager");
                if(old_manager == "") {
                    section.section_btns.get(3).setAttribute("disabled", true);
                }else {
                    //设置版主选择列表
                    _init_manager_select(old_manager);
                }
                //添加子版块面板可能是打开的，关闭
                $("#add_child").hide().prev().show();
            }
        });
      }

      /**
       * [_init_manager_select 初始化删除版主下拉列表]
       * select id为managers
       * @param  {[type]} old_manager [description]
       */
      function _init_manager_select(old_manager) {
        var array = old_manager.split(",");
        var manager, option;
        var $select = $("#managers");
        for(var i = 0;i < array.length;i ++) {
            manager = array[i];
            option = $("<option></option>");
            option.val(manager).html(manager);
            $select.append(option);
        }
      }

      /**
       * [_reset_section_ops 重置板块操作面板，即显示op_default面板]
       */
      function _reset_section_ops() {
        $(".op_area").hide();
        $(".op_default").show();
      }

      /**
       * [_init_section_btns 初始化板块操作按钮]
       * @return {[type]} [void]
       */
      function _init_section_operation_btns() {
        if(section.section_btns == null) {
            section.section_btns = $(".btns").children("button");
        }
         //按钮事件监听
        var ops = $(".op_area");
        //板块操作按钮
        section.section_btns.click(function() {
          var id = $(this).attr("name");
          ops.each(function() {
            if($(this).attr("id") == id) {
              $(this).show();
            }else {
              $(this).hide();
            }
          });
        });
      }
        
     /**
      * [[保存子版块]]
      * @param {[[dom]]} btn [[保存按钮]]
      */
     function save_child_section(btn) {
         //板块名称和版主
         var name = $("#s_c_n");
         var manager = $("#s_c_m");
         var error_span = $(btn).next();
         var name_value = name.val().trim();
         if(name_value == "") {
             error_span.html("请输入板块名称");
             name.focus();
             return;
         }
         var url = "admin/section/save.html";
         //ajax提交请求
         $.post(
             url,
             {
                 "psid" : section.sid,
                 "name" : name_value,
                 "manager" : manager.val().trim()
             },
             _handle_response,
             "json"
         )
     }

     /**
      * [_handle_response 处理请求结果]
      * @param  {[type]} json [返回的json数据]
      */
     function _handle_response(json) {
        if(json.result == "1") {
            show_success(json.message);
            //重新加载
            _init_section_list_panel();
        }else if(json.result == "0") {
            show_error(json.message);
        }
     }
        
    /**
     * [[删除板块]]
     */
    function delete_section() {
        if(confirm("您确定删除此板块?")) {
            var url = "admin/section/delete.html";
            $.post(
                url,
                {"id" : section.sid},
                _handle_response,
                "json"
            );
        }
    }
    
    /**
     * [[修改板块]]
     * @param {[[Type]]} btn [[触发的按钮]]
     */
    function modify_section(btn) {
        var name = $("#m_s_n");
        var error_span = $(btn).next();
        if(name.val().trim() == "") {
            error_span.html("请输入新的板块名称");
            name.focus();
            return;
        }
        var url = "admin/section/update.html";
        $.post(
            url,
            {
                "id" : section.sid,
                "name" : name.val().trim()
            },
           _handle_response,
           "json"
        )
    }
        
    /**
        删除版主 参数同上
        下拉列表idmanagers
     */
    function remove_manager(btn) {
        var error_span = $(btn).next();
        var selected_options = $("#managers option:gt(0):selected");
        if(selected_options.length == 0) {
            error_span.html("请选择需要删除的版主");
            return;
        }
        var url = "admin/section/manager/delete.html";
        //选中的版主
        var selected_mamagers_str = _generate_managers_str(selected_options);
         //没有选中的版主
        var unselected_options = $("#managers option:gt(0):not(:selected)");
        var unselected_managers_str = _generate_managers_str(unselected_options);
        $.post(
            url,
            {
                "id" : section.sid,
                "removeManagers" : selected_mamagers_str,
                "managers" : unselected_managers_str
            },
            _handle_response,
            "json"
        );
    }

    /**
     * [_generate_manages_str 根据选项生成版主字符串，比如skywalker haha]
     * @param  {[jquery对象]} options [选中/未选中的选项数组]
     * @return {[String]}
     */
    function _generate_managers_str(options) {
        var array = new Array();
        options.each(function(index, option) {
            array.push("'" + $(option).val() + "'");
        });
        return array.join(",");
    }