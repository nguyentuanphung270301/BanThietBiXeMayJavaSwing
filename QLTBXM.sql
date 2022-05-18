USE [master]
GO
/****** Object:  Database [QuanLyThietBiXeMay]    Script Date: 5/18/2022 9:32:59 PM ******/
CREATE DATABASE [QuanLyThietBiXeMay]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyThietBiXeMay', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLyThietBiXeMay.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyThietBiXeMay_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLyThietBiXeMay_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyThietBiXeMay].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyThietBiXeMay', N'ON'
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET QUERY_STORE = OFF
GO
USE [QuanLyThietBiXeMay]
GO
/****** Object:  Table [dbo].[CHUCVU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHUCVU](
	[MACHUCVU] [varchar](50) NOT NULL,
	[TENCHUCVU] [nvarchar](50) NOT NULL,
	[LUONGCOBAN] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MACHUCVU] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTDDH]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTDDH](
	[MADDH] [varchar](50) NOT NULL,
	[MATHIETBI] [varchar](50) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [money] NULL,
 CONSTRAINT [PK_CTDDH] PRIMARY KEY CLUSTERED 
(
	[MADDH] ASC,
	[MATHIETBI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTPN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPN](
	[MAPN] [varchar](50) NOT NULL,
	[MATHIETBI] [varchar](50) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [money] NULL,
 CONSTRAINT [PK_CTPN] PRIMARY KEY CLUSTERED 
(
	[MAPN] ASC,
	[MATHIETBI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTPX]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPX](
	[MAPX] [varchar](50) NOT NULL,
	[MATHIETBI] [varchar](50) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [money] NULL,
 CONSTRAINT [PK_CTPX] PRIMARY KEY CLUSTERED 
(
	[MAPX] ASC,
	[MATHIETBI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DATHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DATHANG](
	[MADDH] [varchar](50) NOT NULL,
	[HOTENKH] [nvarchar](50) NOT NULL,
	[DIACHI] [nvarchar](100) NOT NULL,
	[SDT] [varchar](11) NOT NULL,
	[MANHANVIEN] [varchar](50) NULL,
	[NGAYDAT] [date] NULL,
	[TRANGTHAI] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MADDH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAITHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAITHIETBI](
	[MALOAI] [varchar](50) NOT NULL,
	[TENLOAI] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MALOAI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANHANVIEN] [varchar](50) NOT NULL,
	[HOTEN] [nvarchar](50) NOT NULL,
	[NGAYSINH] [date] NULL,
	[GIOITINH] [nchar](3) NULL,
	[DIACHI] [nvarchar](100) NULL,
	[SDT] [varchar](11) NULL,
	[EMAIL] [varchar](100) NULL,
	[BACLUONG] [float] NULL,
	[MACHUCVU] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MANHANVIEN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHASANXUAT]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHASANXUAT](
	[MANSX] [varchar](50) NOT NULL,
	[TENNSX] [nvarchar](50) NOT NULL,
	[DIACHI] [nvarchar](100) NULL,
	[SDT] [varchar](11) NULL,
	[EMAIL] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MANSX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHIEUNHAP]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIEUNHAP](
	[MAPN] [varchar](50) NOT NULL,
	[NGAYNHAP] [date] NOT NULL,
	[MANHANVIEN] [varchar](50) NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAPN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHIEUXUAT]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIEUXUAT](
	[MAPX] [varchar](50) NOT NULL,
	[NGAYBAN] [date] NULL,
	[MANHANVIEN] [varchar](50) NULL,
	[GIOBAN] [time](7) NULL,
	[TIENNHAN] [money] NULL,
	[TRANGTHAI] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAPX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[TENDANGNHAP] [varchar](50) NOT NULL,
	[MATKHAU] [varchar](50) NOT NULL,
	[MANHANVIEN] [varchar](50) NULL,
	[NGAYTAO] [date] NULL,
	[LOAITAIKHOAN] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[TENDANGNHAP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[THIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THIETBI](
	[MATHIETBI] [varchar](50) NOT NULL,
	[TENTHIETBI] [nvarchar](50) NOT NULL,
	[MALOAI] [varchar](50) NULL,
	[MANSX] [varchar](50) NULL,
	[TGBAOHANH] [nvarchar](50) NULL,
	[SOLUONG] [int] NOT NULL,
	[GIA] [money] NULL,
	[HINHANH] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[MATHIETBI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[DATHANG] ADD  CONSTRAINT [CK_TRANGTHAI]  DEFAULT ((0)) FOR [TRANGTHAI]
GO
ALTER TABLE [dbo].[NHANVIEN] ADD  DEFAULT (N'NAM') FOR [GIOITINH]
GO
ALTER TABLE [dbo].[PHIEUNHAP] ADD  DEFAULT ((0)) FOR [TRANGTHAI]
GO
ALTER TABLE [dbo].[PHIEUXUAT] ADD  DEFAULT ((0)) FOR [TRANGTHAI]
GO
ALTER TABLE [dbo].[THIETBI] ADD  CONSTRAINT [SOLUONGMACDINH]  DEFAULT ((0)) FOR [SOLUONG]
GO
ALTER TABLE [dbo].[CTDDH]  WITH CHECK ADD  CONSTRAINT [FK_CTDDH] FOREIGN KEY([MADDH])
REFERENCES [dbo].[DATHANG] ([MADDH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTDDH] CHECK CONSTRAINT [FK_CTDDH]
GO
ALTER TABLE [dbo].[CTDDH]  WITH CHECK ADD  CONSTRAINT [FK_CTDDH_1] FOREIGN KEY([MATHIETBI])
REFERENCES [dbo].[THIETBI] ([MATHIETBI])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTDDH] CHECK CONSTRAINT [FK_CTDDH_1]
GO
ALTER TABLE [dbo].[CTPN]  WITH CHECK ADD  CONSTRAINT [FK__CTPN__MAPN__534D60F1] FOREIGN KEY([MAPN])
REFERENCES [dbo].[PHIEUNHAP] ([MAPN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPN] CHECK CONSTRAINT [FK__CTPN__MAPN__534D60F1]
GO
ALTER TABLE [dbo].[CTPN]  WITH CHECK ADD  CONSTRAINT [FK__CTPN__MATHIETBI__5441852A] FOREIGN KEY([MATHIETBI])
REFERENCES [dbo].[THIETBI] ([MATHIETBI])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CTPN] CHECK CONSTRAINT [FK__CTPN__MATHIETBI__5441852A]
GO
ALTER TABLE [dbo].[CTPX]  WITH CHECK ADD  CONSTRAINT [FK_CTPX] FOREIGN KEY([MAPX])
REFERENCES [dbo].[PHIEUXUAT] ([MAPX])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPX] CHECK CONSTRAINT [FK_CTPX]
GO
ALTER TABLE [dbo].[CTPX]  WITH CHECK ADD  CONSTRAINT [FK_CTPX_1] FOREIGN KEY([MATHIETBI])
REFERENCES [dbo].[THIETBI] ([MATHIETBI])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPX] CHECK CONSTRAINT [FK_CTPX_1]
GO
ALTER TABLE [dbo].[DATHANG]  WITH CHECK ADD  CONSTRAINT [FK_DATHANG] FOREIGN KEY([MANHANVIEN])
REFERENCES [dbo].[NHANVIEN] ([MANHANVIEN])
ON UPDATE CASCADE
ON DELETE SET DEFAULT
GO
ALTER TABLE [dbo].[DATHANG] CHECK CONSTRAINT [FK_DATHANG]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NHANVIEN] FOREIGN KEY([MACHUCVU])
REFERENCES [dbo].[CHUCVU] ([MACHUCVU])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NHANVIEN]
GO
ALTER TABLE [dbo].[PHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK__PHIEUNHAP__MANHA__5070F446] FOREIGN KEY([MANHANVIEN])
REFERENCES [dbo].[NHANVIEN] ([MANHANVIEN])
ON UPDATE CASCADE
ON DELETE SET DEFAULT
GO
ALTER TABLE [dbo].[PHIEUNHAP] CHECK CONSTRAINT [FK__PHIEUNHAP__MANHA__5070F446]
GO
ALTER TABLE [dbo].[PHIEUXUAT]  WITH CHECK ADD  CONSTRAINT [FK_PHIEUXUAT] FOREIGN KEY([MANHANVIEN])
REFERENCES [dbo].[NHANVIEN] ([MANHANVIEN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PHIEUXUAT] CHECK CONSTRAINT [FK_PHIEUXUAT]
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [FK_TAIKHOAN] FOREIGN KEY([MANHANVIEN])
REFERENCES [dbo].[NHANVIEN] ([MANHANVIEN])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [FK_TAIKHOAN]
GO
ALTER TABLE [dbo].[THIETBI]  WITH CHECK ADD  CONSTRAINT [FK_THIETBI] FOREIGN KEY([MALOAI])
REFERENCES [dbo].[LOAITHIETBI] ([MALOAI])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[THIETBI] CHECK CONSTRAINT [FK_THIETBI]
GO
ALTER TABLE [dbo].[THIETBI]  WITH CHECK ADD  CONSTRAINT [FK_THIETBI_1] FOREIGN KEY([MANSX])
REFERENCES [dbo].[NHASANXUAT] ([MANSX])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[THIETBI] CHECK CONSTRAINT [FK_THIETBI_1]
GO
ALTER TABLE [dbo].[CHUCVU]  WITH CHECK ADD  CONSTRAINT [CK_CHUCVU] CHECK  (([LUONGCOBAN]>=(1000000) AND [LUONGCOBAN]<=(100000000)))
GO
ALTER TABLE [dbo].[CHUCVU] CHECK CONSTRAINT [CK_CHUCVU]
GO
ALTER TABLE [dbo].[DATHANG]  WITH CHECK ADD CHECK  (([TRANGTHAI]=(0) OR [TRANGTHAI]=(1)))
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [CHECK_GIOITINH] CHECK  (([GIOITINH]=N'Nam' OR [GIOITINH]=N'Nữ'))
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [CHECK_GIOITINH]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD CHECK  (([GIOITINH]=N'NAM' OR [GIOITINH]=N'NỮ'))
GO
ALTER TABLE [dbo].[PHIEUNHAP]  WITH CHECK ADD CHECK  (([TRANGTHAI]=(0) OR [TRANGTHAI]=(1)))
GO
ALTER TABLE [dbo].[PHIEUXUAT]  WITH CHECK ADD CHECK  (([TRANGTHAI]=(0) OR [TRANGTHAI]=(1)))
GO
ALTER TABLE [dbo].[THIETBI]  WITH CHECK ADD  CONSTRAINT [CK_SOLUONG] CHECK  (([SOLUONG]>=(0)))
GO
ALTER TABLE [dbo].[THIETBI] CHECK CONSTRAINT [CK_SOLUONG]
GO
/****** Object:  StoredProcedure [dbo].[SP_CHUYENTRANGTHAIDATHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_CHUYENTRANGTHAIDATHANG]
@MADDH VARCHAR(50)
AS
UPDATE DATHANG SET TRANGTHAI = 1 WHERE MADDH = @MADDH
GO
/****** Object:  StoredProcedure [dbo].[SP_CHUYENTRANGTHAINHAPHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_CHUYENTRANGTHAINHAPHANG]
@MAPN VARCHAR(50)
AS
UPDATE PHIEUNHAP SET TRANGTHAI = 1 WHERE MAPN = @MAPN 
GO
/****** Object:  StoredProcedure [dbo].[SP_DANGNHAP]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_DANGNHAP]
@TENDANGNHAP VARCHAR(50) , @MATKHAU VARCHAR(50)
AS
SELECT TK.TENDANGNHAP, TK.MATKHAU, NV.HOTEN,TK.MANHANVIEN,TK.LOAITAIKHOAN, TK.NGAYTAO
FROM DBO.TAIKHOAN AS TK, DBO.NHANVIEN AS NV WHERE @TENDANGNHAP = TK.TENDANGNHAP AND @MATKHAU = TK.MATKHAU 
AND TK.MANHANVIEN =NV.MANHANVIEN
GO
/****** Object:  StoredProcedure [dbo].[SP_DOIMATKHAU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_DOIMATKHAU]
@TENDANGNHAP VARCHAR(50),
@MATKHAU VARCHAR(50)
AS
UPDATE TAIKHOAN SET MATKHAU = @MATKHAU WHERE @TENDANGNHAP = TAIKHOAN.TENDANGNHAP
GO
/****** Object:  StoredProcedure [dbo].[SP_KTDOIMATKHAU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KTDOIMATKHAU]
@TENDANGNHAP VARCHAR(50) , @MATKHAU VARCHAR(50)
AS
SELECT * FROM DBO.TAIKHOAN WHERE @TENDANGNHAP = TAIKHOAN.TENDANGNHAP AND @MATKHAU = TAIKHOAN.MATKHAU
GO
/****** Object:  StoredProcedure [dbo].[SP_KTTHEMCHUCVU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KTTHEMCHUCVU]
@MACHUCVU VARCHAR(50)
AS 
SELECT * FROM CHUCVU WHERE MACHUCVU = @MACHUCVU
GO
/****** Object:  StoredProcedure [dbo].[SP_KTTHEMNHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KTTHEMNHANVIEN]
@MANHANVIEN VARCHAR(50)
AS
SELECT * FROM NHANVIEN WHERE NHANVIEN.MANHANVIEN = @MANHANVIEN
GO
/****** Object:  StoredProcedure [dbo].[SP_KTTHIETBIDATHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KTTHIETBIDATHANG]
@MADDH VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
SELECT * FROM CTDDH WHERE MADDH = @MADDH AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_KTTHIETBINHAPHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KTTHIETBINHAPHANG]
@MAPN VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
SELECT * FROM CTPN WHERE MAPN = @MAPN AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_KTTHIETBITRONGHOADON]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_KTTHIETBITRONGHOADON]
@MAPX VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
SELECT * FROM CTPX WHERE MAPX = @MAPX AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYCHUCVU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYCHUCVU]
AS
SELECT * FROM CHUCVU
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYCTDATHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYCTDATHANG]
@MADDH VARCHAR(50)
AS
SELECT * FROM CTDDH WHERE MADDH = @MADDH
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYCTNHAPHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYCTNHAPHANG]
@MAPN VARCHAR(50)
AS
SELECT * FROM CTPN WHERE MAPN = @MAPN
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYDONHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYDONHANG]
@MADDH VARCHAR(50)
AS
SELECT CTDDH.MADDH, CTDDH.MATHIETBI, TB.TENTHIETBI, CTDDH.SOLUONG, CTDDH.DONGIA FROM CTDDH , THIETBI AS TB 
WHERE CTDDH.MATHIETBI = TB.MATHIETBI AND CTDDH.MADDH= @MADDH
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYHOADON]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYHOADON] 
@MAPX varchar(50)
AS
SELECT CT.MAPX, CT.MATHIETBI, TB.TENTHIETBI, CT.SOLUONG , CT.DONGIA FROM CTPX AS CT, THIETBI AS TB , PHIEUXUAT AS PX
WHERE PX.MAPX = @MAPX AND CT.MATHIETBI = TB.MATHIETBI AND CT.MAPX = PX.MAPX AND PX.TRANGTHAI = 0
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYLOAITHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYLOAITHIETBI]
@MALOAI VARCHAR(50)
AS
SELECT * FROM LOAITHIETBI WHERE MALOAI = @MAlOAI
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYMANHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYMANHANVIEN]
@MACHUCVU VARCHAR(50)
AS
SELECT * FROM NHANVIEN WHERE @MACHUCVU = MACHUCVU
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYMATHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYMATHIETBI]
@MATHIETBI VARCHAR(50)
AS
SELECT * FROM THIETBI WHERE MATHIETBI = @MATHIETBI 
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYNHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYNHANVIEN]
AS
SELECT NV.MANHANVIEN, NV.HOTEN, CV.TENCHUCVU , NV.BACLUONG, NV.NGAYSINH, NV.GIOITINH, NV.DIACHI, NV.SDT, NV.EMAIL , LUONG= NV.BACLUONG * CV.LUONGCOBAN
FROM NHANVIEN AS NV, CHUCVU AS CV WHERE NV.MACHUCVU = CV.MACHUCVU
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYNHANVIENCHUATAOTK]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYNHANVIENCHUATAOTK]
AS
SELECT NV.MANHANVIEN, NV.HOTEN, CV.TENCHUCVU , NV.BACLUONG, NV.NGAYSINH, NV.GIOITINH, NV.DIACHI, NV.SDT, NV.EMAIL, LUONG= NV.BACLUONG * CV.LUONGCOBAN
FROM NHANVIEN AS NV, CHUCVU AS CV WHERE NV.MACHUCVU = CV.MACHUCVU AND NV.MANHANVIEN NOT IN  (SELECT MANHANVIEN FROM TAIKHOAN )

GO
/****** Object:  StoredProcedure [dbo].[SP_LAYNHASANXUAT]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYNHASANXUAT]
@MANSX VARCHAR(50)
AS
SELECT * FROM NHASANXUAT WHERE MANSX = @MANSX
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYPHIEUNHAP]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYPHIEUNHAP]
@MAPN VARCHAR(50)
AS
SELECT CTPN.MAPN, CTPN.MATHIETBI, TB.TENTHIETBI, CTPN.SOLUONG, CTPN.DONGIA FROM CTPN , THIETBI AS TB 
WHERE CTPN.MATHIETBI = TB.MATHIETBI AND CTPN.MAPN= @MAPN
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYTAIKHOAN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYTAIKHOAN]
AS
SELECT TK.TENDANGNHAP, TK.MATKHAU, TK.MANHANVIEN, TK.LOAITAIKHOAN, TK.NGAYTAO FROM TAIKHOAN AS TK , NHANVIEN WHERE TK.MANHANVIEN = NHANVIEN.MANHANVIEN 
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYTHIETBITHEOMALOAI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYTHIETBITHEOMALOAI]
@MALOAI VARCHAR(50)
AS
SELECT * FROM THIETBI WHERE MALOAI = @MALOAI
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYTHONGTINDATHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYTHONGTINDATHANG] 
@MADDH VARCHAR(50)
AS
SELECT * FROM DATHANG WHERE MADDH = @MADDH
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYTHONGTINNAHPHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYTHONGTINNAHPHANG]
@MAPN VARCHAR(50)
AS
SELECT * FROM PHIEUNHAP WHERE MAPN = @MAPN
GO
/****** Object:  StoredProcedure [dbo].[SP_LAYTHONGTINNHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_LAYTHONGTINNHANVIEN]
@MANHANVIEN VARCHAR(50)
AS
SELECT * FROM NHANVIEN WHERE MANHANVIEN = @MANHANVIEN
GO
/****** Object:  StoredProcedure [dbo].[SP_SUACHUCVU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUACHUCVU]
@MACHUCVU VARCHAR(50), @TENCHUCVU NVARCHAR(50), @LUONGCOBAN MONEY
AS
UPDATE CHUCVU SET TENCHUCVU = @TENCHUCVU, LUONGCOBAN = @LUONGCOBAN WHERE MACHUCVU = @MACHUCVU
GO
/****** Object:  StoredProcedure [dbo].[SP_SUADONDATHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUADONDATHANG]
@MADDH VARCHAR(50),
@HOTENKH NVARCHAR(50),
@DIACHI NVARCHAR(100),
@SDT VARCHAR(11),
@NGAYDAT DATE
AS
UPDATE DATHANG SET HOTENKH = @HOTENKH , DIACHI = @DIACHI, SDT = @SDT , NGAYDAT = @NGAYDAT WHERE MADDH = @MADDH
GO
/****** Object:  StoredProcedure [dbo].[SP_SUALOAITHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUALOAITHIETBI]
@MALOAI VARCHAR(50),
@TENLOAI NVARCHAR(50),
@MALOAITHAYDOI VARCHAR(50)
AS
UPDATE LOAITHIETBI SET MALOAI = @MALOAI , TENLOAI = @TENLOAI WHERE MALOAI = @MALOAITHAYDOI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUANHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUANHANVIEN]
@MANHANVIEN VARCHAR(50), @HOTEN NVARCHAR(50),@NGAYSINH DATE, @GIOITINH NVARCHAR(3),@DIACHI NVARCHAR(100), @SDT VARCHAR(11),
@EMAIL VARCHAR(100), @BACLUONG FLOAT, @MACHUCVU VARCHAR(50), @MANHANVIENTHAYDOI VARCHAR(50)
AS
UPDATE NHANVIEN SET MANHANVIEN = @MANHANVIEN, HOTEN = @HOTEN,NGAYSINH = @NGAYSINH,GIOITINH = @GIOITINH,DIACHI = @DIACHI,SDT = @SDT,
EMAIL = @EMAIL,BACLUONG = @BACLUONG,MACHUCVU = @MACHUCVU WHERE NHANVIEN.MANHANVIEN = @MANHANVIENTHAYDOI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUANHASANXUAT]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUANHASANXUAT]
@MANSX VARCHAR(50),
@TEN NVARCHAR(50),
@DIACHI NVARCHAR(100),
@SDT VARCHAR(11),
@EMAIL VARCHAR(100),
@MANSXTHAYDOI VARCHAR(50)
AS
UPDATE NHASANXUAT SET MANSX = @MANSX , TENNSX = @TEN , DIACHI = @DIACHI, SDT = @SDT , EMAIL = @EMAIL WHERE MANSX = @MANSXTHAYDOI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUAPHIEUNHAP]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUAPHIEUNHAP]
@MAPN VARCHAR(50),
@NGAYNHAP DATE
AS
UPDATE PHIEUNHAP SET  NGAYNHAP = @NGAYNHAP WHERE MAPN = @MAPN
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATAIKHOAN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUATAIKHOAN]
@TENDANGNHAP VARCHAR(50),@LOAITAIKHOAN NVARCHAR(50) ,@NGAYTAO DATE,
@TENDANGNHAPTHAYDOI VARCHAR(50)
AS
UPDATE TAIKHOAN SET TENDANGNHAP=@TENDANGNHAP,LOAITAIKHOAN = @LOAITAIKHOAN, NGAYTAO=@NGAYTAO
WHERE TENDANGNHAP = @TENDANGNHAPTHAYDOI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUATHIETBI]
@TENTHIETBI NVARCHAR(50),
@MALOAI VARCHAR(50),
@MANSX VARCHAR(50),
@TGBAOHANH NVARCHAR(50),
@GIA MONEY,
@HINHANH IMAGE,
@MATHIETBI VARCHAR(50)
AS
UPDATE THIETBI SET  TENTHIETBI = @TENTHIETBI , MALOAI = @MALOAI,
MANSX = @MANSX, TGBAOHANH = @TGBAOHANH , GIA = @GIA , HINHANH = @HINHANH WHERE MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATHIETBIDONHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUATHIETBIDONHANG]
@MADDH VARCHAR(50),
@MATHIETBI VARCHAR(50),
@SOLUONG INT,
@THANHTIEN MONEY
AS
UPDATE CTDDH SET SOLUONG = @SOLUONG , DONGIA = @THANHTIEN WHERE MADDH = @MADDH AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATHIETBINHAPHANG]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUATHIETBINHAPHANG]

@MAPN VARCHAR(50),
@MATHIETBI VARCHAR(50),
@SOLUONG INT,
@THANHTIEN MONEY
AS
UPDATE CTPN SET SOLUONG = @SOLUONG , DONGIA = @THANHTIEN WHERE MAPN = @MAPN AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATHIETBITRONGHOADON]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUATHIETBITRONGHOADON]
@SOLUONG INT,
@DONGIA MONEY,
@MAPX VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
UPDATE CTPX SET SOLUONG = @SOLUONG, DONGIA = @DONGIA WHERE MAPX = @MAPX AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_THAYDOISOLUONGTHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THAYDOISOLUONGTHIETBI]
@SOLUONG INT,
@MATHIETBI VARCHAR(50)
AS
UPDATE THIETBI SET SOLUONG = @SOLUONG WHERE MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMCHUCVU]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMCHUCVU]
@MACHUCVU VARCHAR(50) , @TENCHUCVU NVARCHAR(50) , @LUONGCOBAN MONEY
AS
INSERT CHUCVU VALUES (@MACHUCVU,@TENCHUCVU,@LUONGCOBAN)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMDONDATHANGMOI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMDONDATHANGMOI]
@MADDH VARCHAR(50),
@HOTENKH NVARCHAR(50),
@DIACHI NVARCHAR(100),
@SDT VARCHAR(11),
@MANHANVIEN VARCHAR(50),
@NGAYDAT DATE,
@TRANGTHAI INT
AS
INSERT INTO DATHANG VALUES(@MADDH,@HOTENKH,@DIACHI,@SDT,@MANHANVIEN,@NGAYDAT,@TRANGTHAI)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMHOADON]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMHOADON]
@MAPX VARCHAR(50),
@TRANGTHAI INT
AS
INSERT INTO PHIEUXUAT(MAPX,TRANGTHAI) VALUES(@MAPX,@TRANGTHAI)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMLOAITHIETBI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMLOAITHIETBI]
@MALOAI VARCHAR(50),
@TENLOAI NVARCHAR(50)
AS
INSERT INTO LOAITHIETBI VALUES(@MALOAI,@TENLOAI)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMNHANVIEN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMNHANVIEN]
@MANHANVIEN VARCHAR(50), @HOTEN NVARCHAR(50), @NGAYSINH DATE, @GIOITINH NVARCHAR(3), @DIACHI NVARCHAR(100), @SDT VARCHAR(11),
@EMAIL VARCHAR(100) , @BACLUONG FLOAT , @MACHUCVU VARCHAR(50)
AS
INSERT NHANVIEN VALUES(@MANHANVIEN,@HOTEN,@NGAYSINH,@GIOITINH,@DIACHI,@SDT,@EMAIL,@BACLUONG,@MACHUCVU)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMNHASANXUAT]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMNHASANXUAT]
@MANSX VARCHAR(50),
@TEN NVARCHAR(50),
@DIACHI NVARCHAR(100),
@SDT VARCHAR(11),
@EMAIL VARCHAR(100)
AS
INSERT INTO NHASANXUAT VALUES (@MANSX, @TEN, @DIACHI, @SDT, @EMAIL)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMPHIEUNHAPMOI]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMPHIEUNHAPMOI]
@MAPN VARCHAR(50),
@NGAYDAT DATE,
@MANHANVIEN VARCHAR(50),
@TRANGTHAI INT
AS
INSERT INTO PHIEUNHAP VALUES(@MAPN,@NGAYDAT,@MANHANVIEN,@TRANGTHAI)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMTAIKHOAN]    Script Date: 5/18/2022 9:32:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMTAIKHOAN]
@TENDANGNHAP VARCHAR(50) , @MATKHAU VARCHAR(50), @MANHANVIEN VARCHAR(50), @NGAYTAO DATE,@LOAITAIKHOAN NVARCHAR(50)
AS
INSERT INTO TAIKHOAN VALUES (@TENDANGNHAP,@MATKHAU,@MANHANVIEN,@NGAYTAO,@LOAITAIKHOAN)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMTHIETBI]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMTHIETBI]
@MATHIETBI VARCHAR(50),
@TENTHIETBI NVARCHAR(50),
@MALOAI VARCHAR(50),
@MANSX VARCHAR(50),
@TGBAOHANH NVARCHAR(50),
@GIA MONEY,
@HINHANH IMAGE
AS
INSERT INTO THIETBI(MATHIETBI,TENTHIETBI,MALOAI,MANSX,TGBAOHANH,GIA,HINHANH) VALUES (@MATHIETBI,@TENTHIETBI,@MALOAI,@MANSX,@TGBAOHANH,@GIA,@HINHANH)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMTHIETBIDONHANG]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMTHIETBIDONHANG]
@MADDH VARCHAR(50),
@MATHIETBI VARCHAR(50),
@SOLUONG INT,
@THANHTIEN MONEY
AS
INSERT INTO CTDDH VALUES(@MADDH,@MATHIETBI,@SOLUONG,@THANHTIEN)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMTHIETBINHAPHANG]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMTHIETBINHAPHANG]
@MAPN VARCHAR(50),
@MATHIETBI VARCHAR(50),
@SOLUONG INT,
@THANHTIEN MONEY
AS
INSERT INTO CTPN VALUES(@MAPN,@MATHIETBI,@SOLUONG,@THANHTIEN)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMTHIETBIVAOHOADON]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMTHIETBIVAOHOADON]
@MAPX VARCHAR(50),
@MATHIETBI VARCHAR(50),
@SOLUONG INT,
@GIA MONEY
AS
INSERT INTO CTPX VALUES(@MAPX,@MATHIETBI,@SOLUONG,@GIA)
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMTHONGTINHOADON]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THEMTHONGTINHOADON]
@MAPX VARCHAR(50),
@NGAYBAN DATE,
@MANHANVIEN VARCHAR(50),
@GIOBAN TIME,
@TIENNHAN MONEY,
@TRANGTHAI INT
AS

UPDATE PHIEUXUAT SET NGAYBAN = @NGAYBAN, MANHANVIEN = @MANHANVIEN, GIOBAN = @GIOBAN, TIENNHAN = @TIENNHAN, TRANGTHAI = @TRANGTHAI WHERE MAPX = @MAPX
GO
/****** Object:  StoredProcedure [dbo].[SP_THONGKEDATHANGTHEONGAY]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THONGKEDATHANGTHEONGAY]
@DATE1 DATE,
@DATE2 DATE
AS
SELECT CT.MADDH, NV.HOTEN, DH.NGAYDAT, SUM(CT.DONGIA) AS 'TONGTIEN' FROM  CTDDH AS CT, DATHANG AS DH, NHANVIEN AS NV
WHERE CT.MADDH =  DH.MADDH AND DH.MANHANVIEN = NV.MANHANVIEN AND DH.TRANGTHAI = 1 AND DH.NGAYDAT BETWEEN @DATE1 AND @DATE2 
GROUP BY CT.MADDH , NV.HOTEN, DH.NGAYDAT
GO
/****** Object:  StoredProcedure [dbo].[SP_THONGKEHOADON]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THONGKEHOADON]
AS
SELECT CT.MAPX, NV.HOTEN, PX.NGAYBAN, SUM(CT.DONGIA) AS 'TONGTIEN' FROM  CTPX AS CT, PHIEUXUAT AS PX, NHANVIEN AS NV
WHERE CT.MAPX =  PX.MAPX AND PX.MANHANVIEN = NV.MANHANVIEN AND PX.TRANGTHAI = 1
GROUP BY CT.MAPX , NV.HOTEN, PX.NGAYBAN
GO
/****** Object:  StoredProcedure [dbo].[SP_THONGKEHOADONTHEONGAY]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THONGKEHOADONTHEONGAY]
@DATE1 DATE,
@DATE2 DATE
AS
SELECT CT.MAPX, NV.HOTEN, PX.NGAYBAN, SUM(CT.DONGIA) AS 'TONGTIEN' FROM  CTPX AS CT, PHIEUXUAT AS PX, NHANVIEN AS NV
WHERE CT.MAPX =  PX.MAPX AND PX.MANHANVIEN = NV.MANHANVIEN AND PX.TRANGTHAI = 1 AND PX.NGAYBAN BETWEEN @DATE1 AND @DATE2
GROUP BY CT.MAPX , NV.HOTEN, PX.NGAYBAN
GO
/****** Object:  StoredProcedure [dbo].[SP_THONGKETDATHANG]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_THONGKETDATHANG]
AS
SELECT CT.MADDH, NV.HOTEN, DH.NGAYDAT, SUM(CT.DONGIA) AS 'TONGTIEN' FROM  CTDDH AS CT, DATHANG AS DH, NHANVIEN AS NV
WHERE CT.MADDH =  DH.MADDH AND DH.MANHANVIEN = NV.MANHANVIEN AND DH.TRANGTHAI = 1
GROUP BY CT.MADDH , NV.HOTEN, DH.NGAYDAT
GO
/****** Object:  StoredProcedure [dbo].[SP_XOACHUCVU]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOACHUCVU]
@MACHUCVU VARCHAR(50)
AS
DELETE FROM CHUCVU WHERE MACHUCVU = @MACHUCVU
GO
/****** Object:  StoredProcedure [dbo].[SP_XOADATHANG]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOADATHANG]
@MADDH VARCHAR(50)
AS
DELETE FROM DATHANG WHERE MADDH = @MADDH
GO
/****** Object:  StoredProcedure [dbo].[SP_XOALOAITHIETBI]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOALOAITHIETBI]
@MALOAI VARCHAR(50)
AS
DELETE FROM LOAITHIETBI WHERE MALOAI = @MALOAI
GO
/****** Object:  StoredProcedure [dbo].[SP_XOANHANVIEN]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOANHANVIEN]
@MANHANVIEN VARCHAR(50)
AS
DELETE FROM NHANVIEN WHERE @MANHANVIEN = NHANVIEN.MANHANVIEN
GO
/****** Object:  StoredProcedure [dbo].[SP_XOANHASANXUAT]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOANHASANXUAT]
@MANSX VARCHAR(50)
AS
DELETE FROM NHASANXUAT WHERE MANSX = @MANSX
GO
/****** Object:  StoredProcedure [dbo].[SP_XOAPHIEUNHAP]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOAPHIEUNHAP]
@MAPN VARCHAR(50)
AS
DELETE FROM PHIEUNHAP WHERE MAPN = @MAPN
GO
/****** Object:  StoredProcedure [dbo].[SP_XOATAIKHOAN]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOATAIKHOAN]
@TENDANGNHAP VARCHAR(50)
AS 
DELETE FROM TAIKHOAN WHERE TENDANGNHAP = @TENDANGNHAP
GO
/****** Object:  StoredProcedure [dbo].[SP_XOATHIETBI]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOATHIETBI]
@MATHIETBI VARCHAR(50)
AS
DELETE  FROM THIETBI WHERE MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_XOATHIETBIKHOIDONHANG]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOATHIETBIKHOIDONHANG]
@MADDH VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
DELETE FROM CTDDH WHERE MADDH = @MADDH AND MATHIETBI = @MATHIETBI
GO
/****** Object:  StoredProcedure [dbo].[SP_XOATHIETBIRAKHOIHOADON]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOATHIETBIRAKHOIHOADON]
@MAPX VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
DELETE FROM CTPX WHERE @MATHIETBI = CTPX.MATHIETBI AND @MAPX = MAPX
GO
/****** Object:  StoredProcedure [dbo].[SP_XOATHIETBIRAKHOIPHIEUNHAP]    Script Date: 5/18/2022 9:33:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_XOATHIETBIRAKHOIPHIEUNHAP]

@MAPN VARCHAR(50),
@MATHIETBI VARCHAR(50)
AS
DELETE FROM CTPN WHERE MAPN = @MAPN AND MATHIETBI = @MATHIETBI
GO
USE [master]
GO
ALTER DATABASE [QuanLyThietBiXeMay] SET  READ_WRITE 
GO
