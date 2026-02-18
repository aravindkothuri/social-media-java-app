import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SocialMediaApp extends JFrame {
    
    private Color primaryColor = new Color(29, 161, 242); // Twitter blue
    private Color secondaryColor = new Color(255, 255, 255);
    private Color backgroundColor = new Color(240, 242, 245);
    private Color textColor = new Color(20, 23, 26);
    private Color lightGray = new Color(245, 245, 245);
    
    public SocialMediaApp() {
        setTitle("SocialConnect");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Set custom background
        getContentPane().setBackground(backgroundColor);
        
        // Create main components
        add(createTopNavigation(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private JPanel createTopNavigation() {
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(secondaryColor);
        navPanel.setPreferredSize(new Dimension(getWidth(), 60));
        navPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 236, 240)));
        
        // Logo and app name
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        
        JLabel logoLabel = new JLabel("🌐");
        logoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        
        JLabel appName = new JLabel("SocialConnect");
        appName.setFont(new Font("Segoe UI", Font.BOLD, 20));
        appName.setForeground(primaryColor);
        
        logoPanel.add(logoLabel);
        logoPanel.add(appName);
        
        // Search bar
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setOpaque(false);
        
        JTextField searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(300, 35));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        searchField.setBackground(backgroundColor);
        
        JButton searchButton = createStyledButton("🔍 Search", primaryColor, secondaryColor);
        searchButton.setPreferredSize(new Dimension(100, 35));
        
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        // User profile section
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profilePanel.setOpaque(false);
        
        JButton notificationsButton = createIconButton("🔔");
        JButton messagesButton = createIconButton("💬");
        JButton profileButton = createIconButton("👤");
        
        profilePanel.add(notificationsButton);
        profilePanel.add(messagesButton);
        profilePanel.add(profileButton);
        
        navPanel.add(logoPanel, BorderLayout.WEST);
        navPanel.add(searchPanel, BorderLayout.CENTER);
        navPanel.add(profilePanel, BorderLayout.EAST);
        
        return navPanel;
    }
    
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        
        // Left sidebar (Navigation)
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        mainPanel.add(createLeftSidebar(), gbc);
        
        // Center feed
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        mainPanel.add(createFeedPanel(), gbc);
        
        // Right sidebar (Trends & Suggestions)
        gbc.gridx = 2;
        gbc.weightx = 0.2;
        mainPanel.add(createRightSidebar(), gbc);
        
        return mainPanel;
    }
    
    private JPanel createLeftSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(secondaryColor);
        sidebar.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        String[] menuItems = {"🏠 Home", "🔍 Explore", "🔔 Notifications", "💬 Messages", 
                              "📋 Lists", "👤 Profile", "⚙️ Settings"};
        
        for (String item : menuItems) {
            JButton menuButton = createMenuButton(item);
            menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            sidebar.add(menuButton);
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        JButton postButton = createStyledButton("➕ Create Post", primaryColor, secondaryColor);
        postButton.setPreferredSize(new Dimension(200, 45));
        postButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        postButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(postButton);
        
        return sidebar;
    }
    
    private JPanel createFeedPanel() {
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBackground(backgroundColor);
        feedPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        
        // Create post composer
        feedPanel.add(createPostComposer());
        feedPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Add sample posts
        feedPanel.add(createPost(
            "John Doe",
            "@johndoe",
            "2h ago",
            "Just launched my new project! Check it out! 🚀",
            "🌐 johndoe.dev",
            "images/project.jpg"
        ));
        
        feedPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        feedPanel.add(createPost(
            "Sarah Johnson",
            "@sarahj",
            "5h ago",
            "Beautiful sunset at the beach today! 🌅",
            "#sunset #beach #weekendvibes",
            "images/sunset.jpg"
        ));
        
        feedPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        feedPanel.add(createPost(
            "Tech News",
            "@technews",
            "1d ago",
            "Breaking: New smartphone released with revolutionary features!",
            "🔗 technews.com/smartphone-2024",
            "images/tech.jpg"
        ));
        
        JScrollPane scrollPane = new JScrollPane(feedPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(backgroundColor);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(backgroundColor);
        container.add(scrollPane, BorderLayout.CENTER);
        
        return container;
    }
    
    private JPanel createPostComposer() {
        JPanel composer = new JPanel(new BorderLayout(10, 10));
        composer.setBackground(secondaryColor);
        composer.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // User avatar
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        
        // Text area
        JTextArea postText = new JTextArea("What's on your mind?");
        postText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        postText.setLineWrap(true);
        postText.setWrapStyleWord(true);
        postText.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        postText.setBackground(lightGray);
        
        // Action buttons
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsPanel.setOpaque(false);
        
        String[] actions = {"📷", "🎥", "📍", "📊"};
        for (String action : actions) {
            JButton actionButton = createIconButton(action);
            actionsPanel.add(actionButton);
        }
        
        // Post button
        JButton postButton = createStyledButton("Post", primaryColor, secondaryColor);
        postButton.setPreferredSize(new Dimension(80, 35));
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(actionsPanel, BorderLayout.WEST);
        bottomPanel.add(postButton, BorderLayout.EAST);
        
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(postText, BorderLayout.CENTER);
        textPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        composer.add(avatar, BorderLayout.WEST);
        composer.add(textPanel, BorderLayout.CENTER);
        
        return composer;
    }
    
    private JPanel createPost(String name, String username, String time, 
                              String content, String link, String imagePath) {
        JPanel post = new JPanel();
        post.setLayout(new BoxLayout(post, BoxLayout.Y_AXIS));
        post.setBackground(secondaryColor);
        post.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);
        
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        
        JPanel userInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userInfo.setOpaque(false);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(textColor);
        
        JLabel usernameLabel = new JLabel(username + " · " + time);
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        usernameLabel.setForeground(Color.GRAY);
        
        userInfo.add(nameLabel);
        userInfo.add(Box.createRigidArea(new Dimension(5, 0)));
        userInfo.add(usernameLabel);
        
        header.add(avatar);
        header.add(userInfo);
        
        // Content
        JLabel contentLabel = new JLabel("<html><body style='width: 300px'>" + content + "</body></html>");
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentLabel.setForeground(textColor);
        
        JLabel linkLabel = new JLabel(link);
        linkLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        linkLabel.setForeground(primaryColor);
        
        // Image placeholder (since we can't display actual images)
        JPanel imagePlaceholder = new JPanel();
        imagePlaceholder.setPreferredSize(new Dimension(400, 200));
        imagePlaceholder.setBackground(lightGray);
        imagePlaceholder.setBorder(new RoundedBorder(10));
        
        JLabel placeholderText = new JLabel("📸 Image Preview");
        placeholderText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        placeholderText.setForeground(Color.GRAY);
        imagePlaceholder.add(placeholderText);
        
        // Actions
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        actions.setOpaque(false);
        
        String[] actionIcons = {"💬 12", "🔄 8", "❤️ 45", "📊 2K"};
        for (String icon : actionIcons) {
            JButton actionButton = createTextButton(icon, Color.GRAY);
            actions.add(actionButton);
        }
        
        post.add(header);
        post.add(Box.createRigidArea(new Dimension(0, 10)));
        post.add(contentLabel);
        post.add(Box.createRigidArea(new Dimension(0, 5)));
        post.add(linkLabel);
        post.add(Box.createRigidArea(new Dimension(0, 10)));
        post.add(imagePlaceholder);
        post.add(Box.createRigidArea(new Dimension(0, 10)));
        post.add(actions);
        
        return post;
    }
    
    private JPanel createRightSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(secondaryColor);
        sidebar.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Trending section
        sidebar.add(createSectionHeader("🔥 Trending"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        
        String[] trends = {
            "#Technology",
            "#Programming",
            "#ArtificialIntelligence",
            "#WebDevelopment",
            "#Java"
        };
        
        for (String trend : trends) {
            JButton trendButton = createTextButton(trend, primaryColor);
            trendButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            sidebar.add(trendButton);
            sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Who to follow section
        sidebar.add(createSectionHeader("👥 Who to follow"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        
        String[][] users = {
            {"Tech Guru", "@techguru", "Follow"},
            {"Code Master", "@codemaster", "Follow"},
            {"Dev Life", "@devlife", "Follow"}
        };
        
        for (String[] user : users) {
            sidebar.add(createUserSuggestion(user[0], user[1], user[2]));
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        return sidebar;
    }
    
    private JPanel createSectionHeader(String title) {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(textColor);
        
        header.add(titleLabel);
        return header;
    }
    
    private JPanel createUserSuggestion(String name, String username, String buttonText) {
        JPanel suggestion = new JPanel(new BorderLayout(10, 0));
        suggestion.setOpaque(false);
        
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        
        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        usernameLabel.setForeground(Color.GRAY);
        
        info.add(nameLabel);
        info.add(usernameLabel);
        
        JButton followButton = createStyledButton(buttonText, primaryColor, secondaryColor);
        followButton.setPreferredSize(new Dimension(80, 30));
        followButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
        
        suggestion.add(avatar, BorderLayout.WEST);
        suggestion.add(info, BorderLayout.CENTER);
        suggestion.add(followButton, BorderLayout.EAST);
        
        return suggestion;
    }
    
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new RoundedBorder(20));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private JButton createIconButton(String icon) {
        JButton button = new JButton(icon);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(primaryColor);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
            }
        });
        
        return button;
    }
    
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(primaryColor);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(textColor);
            }
        });
        
        return button;
    }
    
    private JButton createTextButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    // Custom rounded border class
    class RoundedBorder extends AbstractBorder {
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new SocialMediaApp());
    }
}
