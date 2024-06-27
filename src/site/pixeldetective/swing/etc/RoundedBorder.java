package site.pixeldetective.swing.etc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// 라운드드된 테두리를 가진 버튼 클래스
class RoundedButton extends JButton {

    private Color backgroundColor;
    private Color foregroundColor;
    private int cornerRadius;

    // 생성자
    public RoundedButton(String text, Color backgroundColor, Color foregroundColor, int cornerRadius) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.cornerRadius = cornerRadius;
        setOpaque(false); // 배경을 투명하게 설정
        setContentAreaFilled(false); // 내용 영역 채우기 비활성화
        setFocusPainted(false); // 포커스 시 테두리 그리기 비활성화
        setBorderPainted(false); // 테두리 그리기 비활성화
        setForeground(foregroundColor);
        setFont(getFont().deriveFont(Font.BOLD, 14)); // 폰트 설정
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 변경
    }

    // 그래픽 그리기
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // 배경 색상 설정
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius));

        // 텍스트 그리기
        super.paintComponent(g2);

        g2.dispose();
    }

    // 그래픽 그리기 (테두리)
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // 테두리 그리기
        g2.setColor(foregroundColor);
        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius));

        g2.dispose();
    }

    // 버튼의 크기 지정
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 40); // 버튼의 기본 크기 설정 (가로, 세로)
    }
}