/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.gde.materials.multiview;

import com.jme3.asset.MaterialKey;
import com.jme3.gde.core.assets.AssetDataObject;
import com.jme3.gde.core.assets.ProjectAssetManager;
import com.jme3.gde.core.sceneexplorer.SceneExplorerTopComponent;
import com.jme3.gde.materials.EditableMaterialFile;
import com.jme3.gde.materials.MaterialProperty;
import com.jme3.gde.core.sceneexplorer.MaterialChangeListener;
import com.jme3.gde.core.sceneexplorer.MaterialChangeProvider;
import com.jme3.gde.core.sceneviewer.SceneViewerTopComponent;
import com.jme3.gde.materials.multiview.widgets.MaterialPropertyWidget;
import com.jme3.gde.materials.multiview.widgets.MaterialWidgetListener;
import com.jme3.gde.materials.multiview.widgets.WidgetFactory;
import com.jme3.material.Material;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.CloneableTopComponent;
import org.openide.windows.Mode;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.jme3.gde.materials.multiview//MaterialEditor//EN",
autostore = false)
public final class MaterialEditorTopComponent extends CloneableTopComponent implements MaterialWidgetListener, MaterialChangeProvider {

    private static MaterialEditorTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "MaterialEditorTopComponent";
    private Lookup lookup;
    private final InstanceContent lookupContents = new InstanceContent();
//    private SaveNode saveNode;
    private DataObject dataObject;
    private EditableMaterialFile materialFile;
    private String materialFileName;
    private String relativeMaterialFileName;
    private ProjectAssetManager manager;
    private SaveCookie saveCookie = new SaveCookieImpl();
    private boolean saveImmediate = true;
    private boolean updateProperties = false;
    private List<MaterialChangeListener> materialListeners = new ArrayList<MaterialChangeListener>();

    public MaterialEditorTopComponent() {
    }

    public MaterialEditorTopComponent(DataObject dataObject) {
        this.dataObject = dataObject;
        materialFileName = dataObject.getPrimaryFile().getPath();
        initWindow();

    }

    private void initWindow() {
        initComponents();
        setName(NbBundle.getMessage(MaterialEditorTopComponent.class, "CTL_MaterialEditorTopComponent"));
        setToolTipText(NbBundle.getMessage(MaterialEditorTopComponent.class, "HINT_MaterialEditorTopComponent"));
        setActivatedNodes(new Node[]{dataObject.getNodeDelegate()});
        ((AssetDataObject) dataObject).setSaveCookie(saveCookie);
        manager = dataObject.getLookup().lookup(ProjectAssetManager.class);
        materialFile = new EditableMaterialFile(dataObject.getPrimaryFile(), dataObject.getLookup().lookup(ProjectAssetManager.class));
        materialFile.read();
        setMatDefList(manager.getMatDefs(), materialFile.getMatDefName());
        try {
            jTextArea1.setText(dataObject.getPrimaryFile().asText());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        jTextArea1.getDocument().addDocumentListener(new DocumentChangeListener());

        updateProperties();
        materialPreviewWidget1.showMaterial(manager, materialFileName);

        relativeMaterialFileName = manager.getRelativeAssetPath(materialFileName);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        texturePanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        statesPanel = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        optionsPanel = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jToolBar3 = new javax.swing.JToolBar();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        materialPreviewWidget1 = new com.jme3.gde.materials.multiview.widgets.MaterialPreviewWidget();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 204, 204));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 0));

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setBorder(null);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(0, 0));

        texturePanel.setBackground(new java.awt.Color(204, 204, 204));
        texturePanel.setLayout(new javax.swing.BoxLayout(texturePanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane3.setViewportView(texturePanel);

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jScrollPane3.TabConstraints.tabTitle"), jScrollPane3); // NOI18N

        jScrollPane9.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane9.setBorder(null);

        statesPanel.setBackground(new java.awt.Color(204, 204, 204));
        statesPanel.setLayout(new javax.swing.BoxLayout(statesPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane9.setViewportView(statesPanel);

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jScrollPane9.TabConstraints.tabTitle"), jScrollPane9); // NOI18N

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setBorder(null);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(220, 0));

        optionsPanel.setBackground(new java.awt.Color(204, 204, 204));
        optionsPanel.setLayout(new javax.swing.BoxLayout(optionsPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(optionsPanel);

        jTabbedPane3.addTab(org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jScrollPane2.TabConstraints.tabTitle_1"), jScrollPane2); // NOI18N

        jToolBar2.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jLabel1.text")); // NOI18N
        jToolBar2.add(jLabel1);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jToolBar2.add(jPanel3);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Common/MatDefs/Misc/SolidColor.j3md", "Common/MatDefs/Misc/VertexColor.j3md", "Common/MatDefs/Misc/SimpleTextured.j3md", "Common/MatDefs/Misc/ColoredTextured.j3md", "Common/MatDefs/Misc/Particle.j3md", "Common/MatDefs/Misc/Sky.j3md", "Common/MatDefs/Gui/Gui.j3md", "Common/MatDefs/Light/Lighting.j3md", "Common/MatDefs/Light/Reflection.j3md", "Common/MatDefs/Misc/ShowNormals.j3md", "Common/MatDefs/Hdr/LogLum.j3md", "Common/MatDefs/Hdr/ToneMap.j3md", "Common/MatDefs/Shadow/PreShadow.j3md", "Common/MatDefs/Shadow/PostShadow.j3md" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(32767, 27));
        jComboBox1.setMinimumSize(new java.awt.Dimension(256, 27));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jComboBox1);

        jToolBar3.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jLabel3.text")); // NOI18N
        jToolBar3.add(jLabel3);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jToolBar3.add(jPanel1);

        jTextField1.setText(org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jTextField1.text")); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jToolBar3.add(jTextField1);

        jCheckBox1.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jCheckBox1.text")); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(materialPreviewWidget1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addContainerGap())))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jCheckBox1))
                    .addComponent(materialPreviewWidget1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(MaterialEditorTopComponent.class, "MaterialEditorTopComponent.jScrollPane1.TabConstraints.tabTitle"), jScrollPane1); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (materialFile != null) {
            updateProperties = true;
            materialFile.setMatDefName((String) jComboBox1.getSelectedItem());
            String string = materialFile.getUpdatedContent();
            jTextArea1.setText(string);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if (materialFile != null) {
            materialFile.setName(jTextField1.getText());
            String string = materialFile.getUpdatedContent();
            jTextArea1.setText(string);
        }
}//GEN-LAST:event_jTextField1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        saveImmediate = jCheckBox1.isSelected();
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private com.jme3.gde.materials.multiview.widgets.MaterialPreviewWidget materialPreviewWidget1;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JPanel statesPanel;
    private javax.swing.JPanel texturePanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized MaterialEditorTopComponent getDefault() {
        if (instance == null) {
            instance = new MaterialEditorTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the MaterialEditorTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized MaterialEditorTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(MaterialEditorTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof MaterialEditorTopComponent) {
            return (MaterialEditorTopComponent) win;
        }
        Logger.getLogger(MaterialEditorTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;//ALWAYS;
    }

    @Override
    public HelpCtx getHelpCtx() {
        HelpCtx ctx = new HelpCtx("sdk.material_editing");
        //this call is for single components:
        //HelpCtx.setHelpIDString(this, "com.jme3.gde.core.sceneviewer");
        return ctx;
    }

    @Override
    public void componentOpened() {
        SceneExplorerTopComponent.getDefault().addMaterialChangeProvider(this);
    }

    @Override
    public void componentClosed() {
        materialPreviewWidget1.cleanUp();
        for (int i = 0; i < texturePanel.getComponentCount(); i++) {
            Component c = texturePanel.getComponent(i);
            if (c instanceof MaterialPropertyWidget) {
                ((MaterialPropertyWidget) c).cleanUp();
            }
        }

        clearMaterialChangeListeners();
        SceneExplorerTopComponent.getDefault().removeMaterialChangeProvider(this);

    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        p.setProperty("MaterialFileName", materialFileName);
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        try {
            String version = p.getProperty("version");
            materialFileName = p.getProperty("MaterialFileName");
            // TODO read your settings according to their version
            dataObject = DataObject.find(FileUtil.toFileObject(new File(materialFileName)));
            initWindow();
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    public String getKey() {
        return relativeMaterialFileName;
    }

    public void addMaterialChangeListener(MaterialChangeListener listener) {
        materialListeners.add(listener);
    }

    public void removeMaterialChangeListener(MaterialChangeListener listener) {
        materialListeners.remove(listener);
    }

    public void clearMaterialChangeListeners() {
        materialListeners.clear();

    }

    public void addAllMaterialChangeListener(List<MaterialChangeListener> listeners) {
        materialListeners.addAll(listeners);
    }

    private class DocumentChangeListener implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            checkSave();
        }

        public void removeUpdate(DocumentEvent e) {
            checkSave();
        }

        public void changedUpdate(DocumentEvent e) {
            checkSave();
        }

        public void updateLog(DocumentEvent e, String action) {
        }

        private void checkSave() {
            if (saveImmediate) {
                try {
                    saveCookie.save();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else {
                dataObject.setModified(true);
            }
            if (updateProperties) {
                updateProperties();
                updateProperties = false;
            }
        }
    }

    private class SaveCookieImpl implements SaveCookie {

        public void save() throws IOException {
            String text = jTextArea1.getText();
            materialFile.setAsText(text);
            dataObject.setModified(false);
            materialPreviewWidget1.showMaterial(manager, materialFileName);
        }
    }

    public void setMatDefList(final String[] strings, String selected) {
        EditableMaterialFile prop = materialFile;
        materialFile = null;
        jComboBox1.removeAllItems();
        jComboBox1.addItem("");

        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            jComboBox1.addItem(string);
        }

//        jComboBox1.addItem("Common/MatDefs/Light/Lighting.j3md");
//        jComboBox1.addItem("Common/MatDefs/Misc/Unshaded.j3md");
//        jComboBox1.addItem("Common/MatDefs/Misc/Particle.j3md");
//        jComboBox1.addItem("Common/MatDefs/Misc/Sky.j3md");
//        jComboBox1.addItem("Common/MatDefs/Gui/Gui.j3md");
//        jComboBox1.addItem("Common/MatDefs/Terrain/TerrainLighting.j3md");
//        jComboBox1.addItem("Common/MatDefs/Terrain/Terrain.j3md");
//        jComboBox1.addItem("Common/MatDefs/Misc/ShowNormals.j3md");
        jComboBox1.setSelectedItem(selected);
        materialFile = prop;
    }

    private void updateProperties() {
        for (int i = 0; i < optionsPanel.getComponents().length; i++) {
            Component component = optionsPanel.getComponents()[i];
            if (component instanceof MaterialPropertyWidget) {
                ((MaterialPropertyWidget) component).registerChangeListener(null);
            }
        }
        for (int i = 0; i < texturePanel.getComponents().length; i++) {
            Component component = texturePanel.getComponents()[i];
            if (component instanceof MaterialPropertyWidget) {
                ((MaterialPropertyWidget) component).registerChangeListener(null);
            }
        }
        optionsPanel.removeAll();
        texturePanel.removeAll();
        List<Component> optionList = new LinkedList<Component>();
        List<Component> colorList = new LinkedList<Component>();
        List<Component> valueList = new LinkedList<Component>();
        List<Component> textureList = new LinkedList<Component>();
        List<Component> otherList = new LinkedList<Component>();
        for (Iterator<Entry<String, MaterialProperty>> it = materialFile.getParameterMap().entrySet().iterator(); it.hasNext();) {
            Entry<String, MaterialProperty> entry = it.next();
            MaterialPropertyWidget widget = WidgetFactory.getWidget(entry.getValue(), manager);
            widget.registerChangeListener(this);
            if ("Boolean".equals(entry.getValue().getType())) {
                optionList.add(widget);
            } else if (entry.getValue().getType().indexOf("Texture") >= 0) {
                textureList.add(widget);
            } else if ("Color".equals(entry.getValue().getType())) {
                colorList.add(widget);
            } else if ("Float".equals(entry.getValue().getType())) {
                valueList.add(widget);
            } else if ("Int".equals(entry.getValue().getType())) {
                valueList.add(widget);
            } else {
                otherList.add(widget);
            }
        }
        for (Iterator<Component> it = textureList.iterator(); it.hasNext();) {
            Component component = it.next();
            texturePanel.add(component);
        }
        for (Iterator<Component> it = optionList.iterator(); it.hasNext();) {
            Component component = it.next();
            optionsPanel.add(component);
        }
        for (Iterator<Component> it = colorList.iterator(); it.hasNext();) {
            Component component = it.next();
            texturePanel.add(component);
        }
        for (Iterator<Component> it = valueList.iterator(); it.hasNext();) {
            Component component = it.next();
            optionsPanel.add(component);
        }
        for (Iterator<Component> it = otherList.iterator(); it.hasNext();) {
            Component component = it.next();
            optionsPanel.add(component);
        }
        jScrollPane2.repaint();
        jScrollPane3.repaint();
        setDisplayName(materialFile.getName() + " - " + materialFile.getMaterialPath());
        EditableMaterialFile prop = materialFile;
        materialFile = null;
        jTextField1.setText(prop.getName());
        materialFile = prop;
        updateStates();
    }

    private void updateStates() {
        for (int i = 0; i < statesPanel.getComponents().length; i++) {
            Component component = statesPanel.getComponents()[i];
            if (component instanceof MaterialPropertyWidget) {
                ((MaterialPropertyWidget) component).registerChangeListener(null);
            }
        }
        statesPanel.removeAll();
        for (Iterator<Entry<String, MaterialProperty>> it = materialFile.getStateMap().entrySet().iterator(); it.hasNext();) {
            Entry<String, MaterialProperty> entry = it.next();
            MaterialPropertyWidget widget = WidgetFactory.getWidget(entry.getValue(), manager);
            widget.registerChangeListener(this);
            statesPanel.add(widget);
        }
    }

    public void propertyChanged(MaterialProperty property) {
        String string = materialFile.getUpdatedContent();
        jTextArea1.setText(string);
        try {
            MaterialKey key = new MaterialKey(manager.getRelativeAssetPath(materialFileName));
            manager.deleteFromCache(key);
            Material material = (Material) manager.loadAsset(key);
            if (material != null) {
                for (MaterialChangeListener listener : materialListeners) {
                    listener.setMaterial(material);
                }
            }
        } catch (Exception e) {
        }

    }
}
