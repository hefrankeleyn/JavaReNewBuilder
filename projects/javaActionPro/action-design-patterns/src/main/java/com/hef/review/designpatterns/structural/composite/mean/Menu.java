package com.hef.review.designpatterns.structural.composite.mean;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单
 * @Date 2022/10/29
 * @Author lifei
 */
public class Menu extends MenuComponent{

    private List<MenuComponent> menuComponents = new ArrayList<>();

    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<MenuComponent> getMenuComponents() {
        return menuComponents;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        this.menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        this.menuComponents.remove(menuComponent);
    }

    @Override
    public void getChild(int i) {
        this.menuComponents.get(i);
    }

    @Override
    public void print() {
        String res = MoreObjects.toStringHelper(Menu.class)
                .add("name", name)
                .add("description", description)
                .toString();
        System.out.println(res);
        System.out.println("-----------------");
        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
    }
}
