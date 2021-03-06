<%@include file="../common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>

</style>
<div id="pjax-container" class="pjax-container container">
	<div class="col-sm-12" >
		<h2>
			1.容量瓶颈
		</h2>
		<ol style="margin-left: 4em;">
			<li>Linux下一个目录中的文件数量以不超过300为宜，这样换算，一个库中的表，不应高于100个左右
				（InnoDB一个文件，MyISAM 三个文件）；</li>
			<li>Mysql最大连接16384，实际应用中受限于服务器性能的限制。</li>
			<li>单表容量受限于操作系统。</li>
		</ol>
	</div>
	
	<div class="col-sm-12">
		<h2>
			2.性能瓶颈
		</h2>
		<ol style="margin-left: 4em;">
			<li>单表容量大于1000W检索数据效率低；</li>
		</ol>
	</div>
	
	<div class="col-sm-12">
		<h2>
			3.数据切分
		</h2>
		<p class="indent">
			数据切分根据其切分规则的类型，可以分为两种切分模式。
			一种是按照不同的表来切分到不同的数据库之上，这种切分可以称之为数据的<span class="lead red">垂直（纵向）切分</span>;
			另外一种规则是根据表中数据的逻辑关系，将同一个表中的数据按照某种条件拆分到多个表中或多台数据库之上，
			这种切分称之为数据的<span class="lead red">水平（横向）切分</span>。
		</p>
		<div>
			<h3>3.1垂直切分</h3>
			<p class="indent">
				数据的垂直切分，也可以称为纵向切分。将数据库想象成由很多个一大块一大块的“数据块”（表）组成，垂直地将这些“数据块”切开，
				然后把他们分散到多台数据库主机上面。这样的切分方法就是垂直（纵向）的数据块切分。
			</p>
			<p class="indent">
				示意图（<span class="lead red">分库</span>）：
			</p>
			<p>
				<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/verticalSharding.png">
			</p>
			<p class="indent">
				垂直切分的优点：
			</p>
			<ul style="margin-left: 3em;">
				<li>数据库的拆分简单明了，才分规则明确；</li>
				<li>应用程序模块清晰明确，整合容易；</li>
				<li>数据维护方便易行，容易定位</li>
			</ul>
			<p class="indent">
				垂直切分的缺点：
			</p>
			<ul style="margin-left: 3em;">
				<li>部分表关联无法再数据库级别完成，要在程序中完成；</li>
				<li>对于访问极其频繁且数据量超大的表仍然存在性能瓶颈，不一定能满足要求；</li>
				<li>事务处理相对复杂；</li>
				<li>库多到一定程度之后，扩展性会受到限制；</li>
				<li>过度切分可能会带来系统获取复杂而难以维护。</li>
			</ul>
			<p class="lead red indent">
				垂直切分主要解决Mysql数据库的容量瓶颈问题,不能解决单表中数据量增长出现的容量及性能问题。
			</p>
		</div>
		<div>
			<h3>3.2水平切分</h3>
			<p class="indent">
				数据的水平切分，也可以称为横向切分。简单的水平切分主要是将某个访问极其频繁的表再按照某个字段的某种规则分散到多个表中，
				每个表包含一部分数据。
			</p>
			<p class="indent">
				示意图（<span class="lead red">分表</span>）：如果userId是数字可直接mod，反之hash之后mod,以下都如此。
			</p>
			<p>
				<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/horizontalSharding.png">
			</p>
			<p>
				
			</p>
			<p class="indent">
				水平切分的优点：
			</p>
			<ul style="margin-left: 3em;">
				<li>表关联基本能够在数据库端全部完成；</li>
				<li>不会存在某些超大型数据量和高负载的表遇到瓶颈的问题；</li>
				<li>应用程序端整体架构改动相对较少；</li>
				<li>事务处理相对简单；</li>
				<li>只要切分规则能定义好，基本上较难遇到扩展性限制。</li>
			</ul>
			<p class="indent">
				水平切分的缺点：
			</p>
			<ul style="margin-left: 3em;">
				<li>切分规则相对复杂，很难抽象出一个能够满足整个数据库的切分规则；</li>
				<li>后期数据的维护难度有所增加，人为手工定位数据更困难；</li>
				<li>应用系统各模块耦合度较高，可能会对后面数据的迁移拆分造成一定的困难。</li>
			</ul>
			<p class="lead red indent">
				水平切分主要解决Mysql数据库的性能瓶颈和单表容量限制问题。
			</p>
		</div>
		
		<div class="col-sm-12">
			<h3>3.3垂直与水平联合切分</h3>
			<p class="indent">
				对于那些负载并不是太大、业务逻辑也相对简单的系统可以通过上面两种切分方法之一来解决扩展性问题，
				对于业务逻辑复杂、系统负载大的系统，就需要将上述两种切分方法结合使用，不同的场景使用不同的切分方法。
			</p>
			<p class="indent">
				示意图（<span class="lead red">分库分表</span>）：
			</p>
			<p>
				<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/shardingGroup1.png">
			</p>
			<p class="indent">
				示意图（<span class="lead red">多库多表</span>）：
			</p>
			<p>
				<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/shardingGroup2.png">
			</p>
<!-- 			<p class="indent"> -->
<!-- 				示意图（<span class="lead red">二维数组</span>）： -->
<!-- 			</p> -->
		</div>
	</div>
	<div class="col-sm-12">
		<h2>
			4.全局主键生成策略
		</h2>
		<p class="indent">
				一旦数据库被切分到多个物理结点上，我们将不能再依赖数据库自身的主键生成机制。
				一方面，某个分区数据库自生成的ID无法保证在全局上是唯一的；
				另一方面，应用程序在插入数据之前需要先获得ID,以便进行SQL路由。
				目前几种可行的主键生成策略和对比详见
				<a target="_blank" style="color: blue;" href="http://cenalulu.github.io/mysql/guid-generate/">
					全局唯一ID生成方案对比
				</a>
		</p>
	</div>
	<div class="col-sm-12">
		<h2>
			5.扩容
		</h2>
		<p class="indent">
				随着业务的不停的发展，数据不停的增长，原先的cluster不足以支撑现有业务，需要对cluster进行扩容,
				为了数据库扩容的数据迁移的可验证性及应用的无感知，需要引入<span class="lead red">逻辑库</span>的概念，引入逻辑库之后的结构如下：
		</p>
		<p>
			<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/shardingGroup3.png">
		</p>
		<p class="indent">
				引入逻辑库之后，不能再直接mod分库数进行数据的路由，新的路由规则为
				<span class="lead red">(hash(userId)%逻辑库数)/(逻辑库数量/物理库数量) = 目标库</span>。
				如上图预分了16个逻辑库2个物理库<br>
				hash(userId)=758965818821182441<br>
				758965818821182441%16 = 9<br>
				9/(16/2) = 1.125 则定位到分库2，也就是上图中的user_1
		</p>
		<p class="indent">
			扩容采用<span class="lead red">双倍扩容</span>，
			不建议采用单台扩容，因为单台扩容重做数据不具备可验证性，双倍扩容会把单台的数据一拆为二，具有可验证性，
			缩容亦如此。
		</p>
		<p>
			<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/expanded.png">
		</p>
		<p class="indent">
				扩容步骤：
		</p>
		<ul style="margin-left: 3em;">
			<li>摘下一个slave,停同步；</li>
			<li>对写记录增量log；</li>
			<li>开始对静态slave做数据，一拆为二；</li>
			<li>回放增量写入，直到追上的所有增量与原cluster保持同步；</li>
			<li>写入切换，由原来的cluster切换到扩容之后的cluster。</li>
		</ul>
	</div>
	<div class="col-sm-12">
		<h2>
			6.sharding逻辑实现层面
		</h2>
		<p class="indent">
			从一个系统的程序架构层面来看，sharding逻辑可以再DAO层、JDBC API层、
			介于DAO与JDBC之间的Spring数据访问封装层以及介于应用程序与数据库之间的代理
			四个层面实现。
		</p>
		<p>
			<img class="img-responsive center-block" alt="" src="${contextPath}/resources/img/sharding/sharding.png">
		</p>
	</div>
	<div class="col-sm-12">
		<h2>
			7.数据聚合
		</h2>
		<p class="indent">
			当数据被垂直切分之后，需要面临业务上需要整合数据显示的问题，对于数据聚合目前有三种方案：
		</p>
		<ul style="margin-left: 3em;">
			<li>数据仓储（DW）,适用于做离线数据，将数据抽取过来，清洗完，
				整合到主题域和多维模型里，然后BI就可以基于主题域和多维模型做各种分析；</li>
			<li>应用之间接口调用，适用于实时数据展示；</li>
			<li>数据视图，适用于实时数据展示。</li>
		</ul>
	</div>
	
	
	<div class="col-sm-12">
		<h2>
			8.分布式事务
		</h2>
		
	</div>
</div>