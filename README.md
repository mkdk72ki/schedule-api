# 概要
特定のグループ内で気軽に予定を共有できるアプリケーションを作成しました。

# 制作背景
友人が皆社会人になってから、なかなか予定が合わず集まることが難しくなってしまい、なんとも寂しく感じていました。
そこで、もっと気軽に交流できる機会が作れないかと考え、このアプリを開発しようと思い立ちました。
  
各々好きな時にやりたいことや空いているということを先に伝えておいて、その時に都合の良い人が参加する、という仕組みにすることで、予定を提示してから日程を調整するより気軽に提案ができるようになり、交流の機会の増加にもつながると考えました。
  
## ER図
[![](https://mermaid.ink/img/pako:eNq9Uk1Lw0AU_CuPd1JooHrMrfiBpdRKrZ4WZM2-pgtJNuwHIkn-u5tsEroqHn2HkJ3MzgyZ12CmBGGKpG8lzzUvWcUsQ2dIG4bQtkmya4DhOxWqymWVezBl6J-BmGvl6r-ZgWiyEwlXkEe7XZK0LcwuIwv8_M6bTCa574JNj8D68QBSwNMG5mNv8Oax-80MDVoBC7zX1f7mYbW_uF5egpW2IAhR4LDe3oFJrCxjiAYounu19JczVZZU2ZCvm2JOf3LIyOwcMhzPzSseVGO07wdeQn4_kWPNjflQWkDkOFfyf5bnnf90hXHiRiD-cNbLJG1xgSXpkkvhN7Tp-QztiXxmTP2roCN3he1XovNU7qx6_qwyTK12tEBXC25pXGtMj7wwHiUhrdLbsPXD8ndf95L5GQ?type=png)](https://mermaid.live/edit#pako:eNq9Uk1Lw0AU_CuPd1JooHrMrfiBpdRKrZ4WZM2-pgtJNuwHIkn-u5tsEroqHn2HkJ3MzgyZ12CmBGGKpG8lzzUvWcUsQ2dIG4bQtkmya4DhOxWqymWVezBl6J-BmGvl6r-ZgWiyEwlXkEe7XZK0LcwuIwv8_M6bTCa574JNj8D68QBSwNMG5mNv8Oax-80MDVoBC7zX1f7mYbW_uF5egpW2IAhR4LDe3oFJrCxjiAYounu19JczVZZU2ZCvm2JOf3LIyOwcMhzPzSseVGO07wdeQn4_kWPNjflQWkDkOFfyf5bnnf90hXHiRiD-cNbLJG1xgSXpkkvhN7Tp-QztiXxmTP2roCN3he1XovNU7qx6_qwyTK12tEBXC25pXGtMj7wwHiUhrdLbsPXD8ndf95L5GQ)

  
## 使用技術  
- Java
- Spring boot
- Spring Security
- Mybatis
- Mysql
- Docker
- thymeleaf

## 機能一覧
- ログイン・ログアウト機能
  
- ユーザー機能
  - ユーザー一覧表示
  - ログイン中のユーザーの情報表示
  - ユーザー削除  
    
- グループ機能
  - グループ一覧表示
  -  加入グループ一覧表示
  -  グループ加入
  -  グループ作成
  -  グループ削除
  
- スケジュール機能
  - スケジュール一覧表示
  -  加入グループのスケジュール一覧表示
  -  グループごと・日付ごとにソート
  -  スケジュール作成
  -  スケジュール削除  

### 一覧表示
![bel](https://github.com/mkdk72ki/schedule-api/assets/143886913/d01d09a9-57d4-47a3-b0ac-a93a49f11468)  

ログインしているユーザーの権限が`ADMIN`の場合、登録されている全てのユーザー、グループ、スケジュールの一覧を見ることができます。

### ユーザー情報の表示
![image](https://github.com/mkdk72ki/schedule-api/assets/143886913/9b41a664-2c3a-4c3f-9617-26556e303397)  

自分のユーザー情報の確認と、ユーザー情報の編集・ユーザーの削除ができます。

### 流れ
#### 1.ログインする or 新規登録する
![image](https://github.com/mkdk72ki/schedule-api/assets/143886913/85df92f0-0ad5-40ca-82cc-a3c4aded0e1d)  
ユーザーID、パスワードを入力して`ログイン`を押します。もしくは、`新規登録`を押してユーザー登録を行います。

![image](https://github.com/mkdk72ki/schedule-api/assets/143886913/3f926021-6242-406a-80c3-cf369abad1c4)
登録の際は ユーザー名、ユーザーID、パスワードを入力し、`作成`を押します。

#### 2.グループを作成 or グループに加入  

- グループを作成する場合  
![grcre](https://github.com/mkdk72ki/schedule-api/assets/143886913/ef535efb-fcf3-496f-abe0-2ec541336cef)
グループ名、グループID、パスワードを入力して`作成`を押します。

- 既存のグループに加入する場合
![grblo](https://github.com/mkdk72ki/schedule-api/assets/143886913/b5b0cacb-b096-4c4d-bdec-d268d7aeec86)
入りたいグループのグループID、パスワードを入力して`加入`を押します。
  
#### 3.スケジュールを登録する  
![schecre](https://github.com/mkdk72ki/schedule-api/assets/143886913/8a30ecc2-31ed-4758-8821-406a387b8a23)
グループ名(選択式)、内容、日付を入力し、`作成`を押します。
また、任意で開始時間、終了時間、詳細を入れることも可能です。

#### 4.スケジュールを確認する
![sch](https://github.com/mkdk72ki/schedule-api/assets/143886913/602820fd-feb4-4030-94e2-2e0a4fcf31ba)
自身が加入しているグループに共有されているスケジュールを確認できます。
また、グループ・日付で絞り込み検索が可能です。
  
## 工夫したところ
- Spring Securityによるログイン機能
- グループ・日付での絞り込み検索機能
- 加入グループのみの一覧表示
- グループに加入しているユーザーの一覧表示

## 今後の実装したい機能
- スケジュール一覧をカレンダー表示にする
- グループパスワードもエンコードして管理する
- ユーザー情報の編集時にパスワードの再入力を不要にする
- 終了したスケジュールを削除する
- いいね機能
- テストコードの実装
- CIの実装
- デプロイ
- 他SNSなどとの連携(通知など)
