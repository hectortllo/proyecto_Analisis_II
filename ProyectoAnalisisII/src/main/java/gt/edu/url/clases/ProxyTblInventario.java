/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import gt.edu.url.entity.Producto;

/**
 *
 * @author Oswaldo Alvarez <mynoswaldo@gmail.com>
 */
public class ProxyTblInventario implements TableModel {

    DefaultTableModel realSubject = new DefaultTableModel();

    public ProxyTblInventario(List<Producto> productos) {
        realSubject.addColumn("No.");
        realSubject.addColumn("Nombre");
        realSubject.addColumn("Precio");
        realSubject.addColumn("Cantidad");
        realSubject.addColumn("Descripción");
        Object[] row = new Object[5];
        for (Producto producto : productos) {
            row[0] = producto.getId();
            row[1] = producto.getNombre();
            row[2] = producto.getPrecio();
            row[3] = producto.getCantidad();
            row[4] = producto.getDescripcion();
            realSubject.addRow(row);
        }
    }

    @Override
    public int getRowCount() {
        return realSubject.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return realSubject.getColumnCount();
    }

    @Override
    public String getColumnName(int i) {
        return realSubject.getColumnName(i);
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return realSubject.getColumnClass(i);
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return realSubject.isCellEditable(i, i1);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return realSubject.getValueAt(i, i1);
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        realSubject.setValueAt(o, i, i1);
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        realSubject.addTableModelListener(tl);
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        realSubject.removeTableModelListener(tl);
    }

}
