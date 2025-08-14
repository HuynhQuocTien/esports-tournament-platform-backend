package com.example.esports_tournament_platform_backend.enums;

public enum TournamentFormat {
    SINGLE_ELIMINATION,          // Loại trực tiếp
    DOUBLE_ELIMINATION,          // Nhánh thắng - thua
    ROUND_ROBIN,                 // Đấu vòng tròn
    GROUP_STAGE,                 // Chia bảng đấu (vòng bảng)
    SWISS_SYSTEM,                // Hệ Thụy Sỹ
    HYBRID                       // Thể thức hỗn hợp: giai đoạn 1 vòng tròn/Thụy Sỹ, giai đoạn 2 loại trực tiếp
}
