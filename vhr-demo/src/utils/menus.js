
// 此时参数routes是后端查询返回的路由参数
export const formatRoutes = (num) => {
    // 将转换好的对象存入该数组
    let rou = [];
    //遍历routes 可以看到后端返回的数据是数组里面包含多个对象
    for (var i = 0; i < num.length; i++) {
        // 将menus里面的属性批量赋值给属性
        let {name, meta, path, component, iconCls, children} = num[i];
        if (num[i].children != null && num[i].children instanceof Array) {
            // 递归调用
            children = formatRoutes(num[i].children);
        }
        let fmRouter = {
            path: path,
            name: name,
            meta: meta,
            iconCls: iconCls,
            children: children,
            component: () => {
                if (component.startsWith('Hom')) {
                    return import('../components/' + component + '.vue');
                } else if (component.startsWith('Emp')) {
                    return import('../components/emp/' + component + '.vue');
                } else if (component.startsWith('Per')) {
                    return import('../components/per/' + component + '.vue');
                } else if (component.startsWith('Sal')) {
                    return import('../components/sal/' + component + '.vue');
                } else if (component.startsWith('Sta')) {
                    return import('../components/sta/' + component + '.vue')
                } else if (component.startsWith('Sys')) {
                    return import('../components/sys/' + component + '.vue')
                }
            }
        };
        rou.push(fmRouter);
    }
    return rou;
}