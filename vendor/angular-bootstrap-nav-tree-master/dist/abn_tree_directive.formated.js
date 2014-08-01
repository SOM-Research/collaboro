var module;

module = angular.module('angularBootstrapNavTree', []);

module.directive('abnTree', function($timeout) {
  return 
  {
    restrict: 'E',
    templateUrl: 'vendor/angular-bootstrap-nav-tree-master/dist/abn_tree_template.html',
    scope:{
      treeData: '=',
      onSelect: '&',
      initialSelection: '='
    },
    link: function(scope, element, attrs) 
    {
      var expand_level, for_each_branch, on_treeData_change, select_branch, selected_branch;
      if (attrs.iconExpand == null)
      {
        attrs.iconExpand = 'fa fa-plus-circle';
      }
      if (attrs.iconCollapse == null)
      {
        attrs.iconCollapse = 'fa fa-minus-circle';
      }
      if (attrs.iconLeaf == null)
      {
        attrs.iconLeaf = 'fa fa-leaf';
      }
      if (attrs.expandLevel == null)
      {
        attrs.expandLevel = '3';
      }
      expand_level = parseInt(attrs.expandLevel, 10);
      scope.header = attrs.header;
      if (!scope.treeData)
      {
        alert('no treeData defined for the tree!');
        debugger;
        return;
      }
      if (scope.treeData.length == null)
      {
        if (treeData.label != null)
        {
          scope.treeData = [treeData];
        }
        else
        {
          alert('treeData should be an array of root branches');
          debugger;
          return;
        }
      }

      for_each_branch = function(f) 
      {
        var do_f, root_branch, _i, _len, _ref, _results;
        do_f = function(branch, level)
        {
          var child, _i, _len, _ref, _results;
          f(branch, level);
          if (branch.children != null)
          {
            _ref = branch.children;
            _results = [];
            for(_i = 0, _len = _ref.length; _i < _len; _i++)
            {
              child = _ref[_i];
              _results.push(do_f(child, level + 1));
            }
            return _results;
          }
        };

        //Aqui ya todas las ramas del arbol tienen seteada las propiedades level y expanded.

        _ref = scope.treeData;
        _results = [];
        for (_i = 0, _len = _ref.length; _i < _len; _i++)
        {
          root_branch = _ref[_i];
          _results.push(do_f(root_branch, 1));
        }
        return _results;
      };

      //Esta es la primera invocacion de una funcion por parte de link().

      for_each_branch
      (
        function(b, level)
        {
          b.level = level;
          return b.expanded = b.level < expand_level;
        }
      );

      selected_branch = null;
      select_branch = function(branch)
      {
        if (branch !== selected_branch)
        {
          if (selected_branch != null)
          {
            selected_branch.selected = false;
          }
          branch.selected = true;
          selected_branch = branch;
          //Cuando se selecciona una rama se verifica si tiene definida una funcion llamada onSelect, en caso que no se le llama un onSelect general para todas las ramas definido en el scope
          if (branch.onSelect != null)
          {
            return 
            $timeout
            (
              function()
              {
                return branch.onSelect(branch);
              }
            );
          } 
          else
          {
            if (scope.onSelect != null)
            {
              return $timeout(function()
              {
                return scope.onSelect(
                {
                  branch: branch
                });
              });
            }
          }
        }
      };

      //Esta funcion esta asociada al hacer click sobre una rama del arbol.
      scope.user_clicks_branch = function(branch) 
      {
        if (branch !== selected_branch)
        {
          return select_branch(branch);
        }
      };

      scope.tree_rows = [];
      
      on_treeData_change = function()
      {
        var add_branch_to_list, root_branch, _i, _len, _ref, _results;
        scope.tree_rows = [];
        for_each_branch
        (
          function(branch)
          {
            if (branch.children)
            {
              if (branch.children.length > 0)
              {
                return branch.children = branch.children.map
                (function(e)
                  {
                    if (typeof e === 'string')
                    {
                      return
                      {
                            label: e,
                            children: []
                      };
                    }
                    else
                    {
                      return e;
                    }
                  }
                );
              }
            }
            else
            {
              return branch.children = [];
            }
          }
        );

        for_each_branch
        (
          function(b, level)
          {
            if (!b.uid)
            {
              return b.uid = "" + Math.random();
            }
          }
        );

        add_branch_to_list = function(level, branch, visible)
        {
          var child, child_visible, tree_icon, _i, _len, _ref, _results;
          if (branch.expanded == null)
          {
            branch.expanded = false;
          }
          if (!branch.children || branch.children.length === 0)
          {
            tree_icon = attrs.iconLeaf;
          }
          else
          {
            if (branch.expanded)
            {
              tree_icon = attrs.iconCollapse;
            } 
            else
            {
              tree_icon = attrs.iconExpand;
            }
          }
          scope.tree_rows.push
          (
            {
              level: level,
              branch: branch,
              label: branch.label,
              tree_icon: tree_icon,
              visible: visible
            }
          );
          if (branch.children != null)
          {
            _ref = branch.children;
            _results = [];
            for (_i = 0, _len = _ref.length; _i < _len; _i++)
            {
              child = _ref[_i];
              child_visible = visible && branch.expanded;
              _results.push(add_branch_to_list(level + 1, child, child_visible));
            }
            return _results;
          }
        };
        _ref = scope.treeData;
        _results = [];
        for (_i = 0, _len = _ref.length; _i < _len; _i++)
        {
          root_branch = _ref[_i];
          _results.push(add_branch_to_list(1, root_branch, true));
        }
        return _results;
      };
      if (attrs.initialSelection != null)
      {
        for_each_branch
        (function(b)
          {
            if (b.label === attrs.initialSelection)
            {
              return select_branch(b);
            }
          }
        );
      }
      return scope.$watch('treeData', on_treeData_change, true);
    }
  };
});
