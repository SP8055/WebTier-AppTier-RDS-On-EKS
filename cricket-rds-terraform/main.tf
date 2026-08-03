provider "aws" {
  region = "ap-south-1"
}

data "aws_vpc" "eks_vpc" {
  id = "vpc-03627fd848189b87c"
}

data "aws_subnets" "eks_subnets" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.eks_vpc.id]
  }
}

resource "aws_db_subnet_group" "cricket_db_subnet" {
  name        = "cricket-db-subnet-group"
  subnet_ids  = data.aws_subnets.eks_subnets.ids
}

resource "aws_security_group" "rds_sg" {
  name   = "cricket-rds-sg"
  vpc_id = data.aws_vpc.eks_vpc.id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = [data.aws_vpc.eks_vpc.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_db_instance" "cricket_db" {
  identifier                  = "cricket-shop-db"
  engine                      = "postgres"
  engine_version              = "16"
  instance_class              = "db.t3.micro"
  allocated_storage           = 20
  db_name                     = "cricketdb"
  username                    = "cricketuser"
  manage_master_user_password = true
  db_subnet_group_name        = aws_db_subnet_group.cricket_db_subnet.name
  vpc_security_group_ids      = [aws_security_group.rds_sg.id]
  publicly_accessible         = false
  skip_final_snapshot         = true

  tags = {
    Name = "cricket-shop-db"
  }
}

output "rds_endpoint" {
  value = aws_db_instance.cricket_db.address
}

output "rds_secret_arn" {
  value = aws_db_instance.cricket_db.master_user_secret[0].secret_arn
}

